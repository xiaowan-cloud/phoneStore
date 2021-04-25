package com.scut.mall.controller.adminUser;

import com.scut.mall.entity.ReturnOrder;
import com.scut.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;


/**
 * @ Author     ：cookie
 * @ Description：${统计模块的后台controller，历史订单总数，总金额，用户总数}
 * @ Modified By：
 */
@Controller
@RequestMapping("/admin/analysis")
public class AdminAnalysisController {
    @Autowired
    private OrderService orderService;

    /**
     * 后台业务统计
     * @param map
     * @return
     */
    @RequestMapping("/toList.html")
    public  String toAnalysisList(ModelMap map){
        /* 获取历史订单总营业额 */
        double moneySum = orderService.getMoneySum();
        map.addAttribute("moneySum", moneySum);
        /* 获取历史订单总用户量 */
        map.addAttribute("orderSum",orderService.getOrderSum());
       int  userSum = orderService.getUserSum();
        map.addAttribute("userSum", userSum);
        //获取每个月订单金额
        List<Tuple> list = orderService.getMonthly();
        List<ReturnOrder> returnOrders = new ArrayList<>() ;
        if(list.size()>0){
            list.stream().forEach(tuple -> {
                ReturnOrder returnOrder = new ReturnOrder();
                returnOrder.setMonths(tuple.get(0).toString());
                returnOrder.setOrdernum(Integer.parseInt(tuple.get(1).toString()));
                returnOrder.setTotal(Double.valueOf(tuple.get(2).toString()));
                returnOrders.add(returnOrder);
            });
        }
        map.addAttribute("returnOrders", returnOrders);
        return "admin/analysis/list";
    }

}
