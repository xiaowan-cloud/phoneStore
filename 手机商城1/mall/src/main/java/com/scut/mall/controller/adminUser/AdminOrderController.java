package com.scut.mall.controller.adminUser;

import com.scut.mall.entity.Order;
import com.scut.mall.entity.OrderItem;
import com.scut.mall.entity.pojo.ResultBean;
import com.scut.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ Author     ：Bin Liu
 * @ Description：后台订单管理控制层类
 * @ Modified By：
 */
@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 打开订单列表页面
     * @return
     */
    @RequestMapping("/toList.html")
    public String toList() {
        return "admin/order/list";
    }

    /**
     * 获取所有订单的总数
     * @return
     */
    @ResponseBody
    @RequestMapping("/getTotal.do")
    public ResultBean<Integer> getTotal() {
        Pageable pageable =PageRequest.of(1, 15, Sort.by(Sort.Direction.ASC,"id"));
        int total = (int) orderService.findAll(pageable).getTotalElements();
        return new ResultBean<>(total);
    }

    /**
     * 获取所有订单
     * @param pageindex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/list.do")
    public ResultBean<List<Order>> listData(int pageindex,
                                            @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        Pageable pageable =PageRequest.of(pageindex, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        List<Order> list = orderService.findAll(pageable).getContent();
        return new ResultBean<>(list);
    }

    /**
     * 获取订单项
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDetail.do")
    public ResultBean<List<OrderItem>> getDetail(int orderId) {
        List<OrderItem> list = orderService.findItems(orderId);
        return new ResultBean<>(list);
    }

    /**
     * 发货
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/send.do")
    public ResultBean<Boolean> send(int id) {
        orderService.updateStatus(id,3);
        return new ResultBean<>(true);
    }

    @RequestMapping("/del.do")
    @ResponseBody
    public ResultBean<Boolean> del(int id) {
        orderService.delById(id);
        return new ResultBean<>(true);
    }
}
