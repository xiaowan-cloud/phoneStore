package com.scut.mall.controller.user;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.google.gson.Gson;
import com.scut.mall.entity.Order;
import com.scut.mall.entity.OrderItem;
import com.scut.mall.entity.User;
import com.scut.mall.entity.pojo.ResultBean;
import com.scut.mall.service.OrderService;
import com.scut.mall.utils.AlipayConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @ Author     ：Bin Liu
 * @ Description：订单控制层类
 * @ Modified By：
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 打开订单列表页面
     *
     * @return
     */
    @RequestMapping("/toList.html")
    public String toOrderList() {
        return "mall/order/list";
    }

    /**
     * 查询用户订单列表
     *
     * @param pageindex,request
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public ResultBean<List<Order>> listData(int pageindex , HttpServletRequest request,
                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageindex, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        List<Order> orders = orderService.findUserOrder(request, pageable).getContent();
        return new ResultBean<>(orders);
    }

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    @RequestMapping("/getDetail.do")
    @ResponseBody
    public ResultBean<List<OrderItem>> getDetail(int orderId) {
        List<OrderItem> orderItems = orderService.findItems(orderId);
        return new ResultBean<>(orderItems);
    }

//    /**
//     * 提交订单
//     *
//     * @param addressId
//     * @param request
//     * @param response
//     */
//    @RequestMapping("/submit.do")
//    public void submit(Integer addressId,
//                       HttpServletRequest request,
//                       HttpServletResponse response) throws Exception {
//        orderService.submit(addressId, request, response);
//    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/submit.do")
    public ResultBean<Boolean> submit(int address, HttpServletRequest request, HttpServletResponse response) throws Exception{

        orderService.submit( address, request, response );
        return new ResultBean<>(true);
    }
    /**
     * 支付方法
     *
     * @param orderId
     */
    @RequestMapping("pay1.do")
    @ResponseBody
    public ResultBean<Boolean> pay1(int orderId,HttpServletRequest request, HttpServletResponse response) throws Exception,IOException {
        orderService.pay(orderId,request,response);
        return new ResultBean<>(true);
    }

    @RequestMapping("pay.do")
    @ResponseBody
    public String pay(int orderId,HttpServletRequest request, HttpServletResponse response) throws Exception,IOException {
    	Order order = orderService.findById(orderId);
    	User user = (User) request.getSession().getAttribute("user");
    	request.getSession().setAttribute("orderId", orderId);
    	String username="";
    	if(user!=null){
    		username=user.getUserName();
    	}
    	Gson gson = new Gson();
    	
    	//商户订单号，商户网站订单系统中唯一订单号，必填
    	String out_trade_no =UUID.randomUUID().toString();  
    	
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		//这里设置支付后跳转的地址
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		//alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		//付款金额，必填
		String total_amount = String.valueOf(order.getTotal());
		//订单名称，必填
		String subject ="用户"+username+"订单";
		//商品描述，可空
		String body = "";

		// 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
    	String timeout_express = "5m";

		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"timeout_express\":\""+ timeout_express +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();

		return result;
    }
    
    
    @RequestMapping("payreturn")
	public String alipayNotifyNotice( HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("支付成功。。。。");
		
		int orderId = (int) request.getSession().getAttribute("orderId");
		orderService.pay(orderId, request, response);
		 return "mall/order/list";
	
}
    
    /**
     * 确认收货
     * @param orderId
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("receive.do")
    @ResponseBody
    public ResultBean<Boolean> receive(int orderId, HttpServletResponse response) throws IOException {
        orderService.receive(orderId);
        return new ResultBean<>(true);
    }
    /**
     * create by: Cillivian
     * description:分页显示
     *
      * @Param: null
     * @return
     */
    @ResponseBody
    @RequestMapping("/getTotal.do")
    public ResultBean<Integer> getTotal() {
        Pageable pageable =  PageRequest.of(1, 15, Sort.by(Sort.Direction.ASC,"id"));
        int total = (int) orderService.findAll(pageable).getTotalElements();
        return new ResultBean<>(total);
    }
}
