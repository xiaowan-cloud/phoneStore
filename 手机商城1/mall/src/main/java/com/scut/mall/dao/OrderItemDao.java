package com.scut.mall.dao;

import com.scut.mall.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Author     ：Bin Liu
 * @ Description：订单项数据访问层接口
 * @ Modified By：
 */
public interface OrderItemDao  extends JpaRepository<OrderItem, Integer> {
    /**
     * create by: Bin Liu
     * description: 根据订单Id查询
     * @Param: null
     * @return 
     */
    List<OrderItem> findByOrderId(int orderId);
}
