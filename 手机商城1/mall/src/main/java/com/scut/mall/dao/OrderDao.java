package com.scut.mall.dao;

import com.scut.mall.entity.Order;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import org.hibernate.mapping.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.List;

/**
 * @ Author     ：Bin Liu
 * @ Description：订单数据访问层接口
 * @ Modified By：cookie
 * @ Description: 增加统计模块所需的内容
 */
public interface OrderDao extends JpaRepository<Order, Integer> {
    /**
     * create by: Bin Liu
     * description: 通过id查找订单
     * @Param: null
     * @return
     */
    Order findOrderById(Integer id);

    /**
     * create by: Bin Liu
     * description: 更改订单状态
     * @Param: state
     * @Param: id
     * @return 
     */
    @Modifying
    @Transactional
    @Query(value = "update `order` o set o.state=?1 where o.id=?2",nativeQuery = true)
    void updateState(int state,int id);

    /**
     * create by: Bin Liu
     * description: 查找用户的订单
     * @Param: userId
     * @return 
     */
    List<Order> findByUserId(int userId);

    /**
     * create by: cookie
     * description: 获取历史订单总额
     */
    @Query(value = "SELECT IFNULL(SUM(total),0) from `order`;",nativeQuery = true)
    double getMoneySum();

    /**
     * create by: cookie
     * description: 获取历史订单总额
     */
    @Query(value = "SELECT COUNT(*) from `user`;",nativeQuery = true)
    int getUserSum();

    /**
     * 获取订单总数
     * @return
     */
    @Query(value = "SELECT COUNT(*) from `order`;",nativeQuery = true)
    int getOrderSum();
    /**
     * create by: cookie
     * description: 获取每月订单量与每月订单总额
     */
    @Query(value = "SELECT DATE_FORMAT(order_time, '%Y年-%m月') months,COUNT(MONTH(order_time)) as ordernum,SUM(total) total FROM `order` GROUP BY months;",nativeQuery = true)
    List<Tuple> getMonthly();

    /**
     * create by: Bin Liu
     * description:
     * @Param: null
     * @return
     */

    Page<Order> findByUserId(Integer userId, Pageable pageable);
}
