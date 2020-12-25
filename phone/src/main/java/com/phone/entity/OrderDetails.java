package com.phone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 订单详情实体类
 *
 * @author makejava
 * @since 2020-12-22 14:31:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    private String orderId;
    /**
     * 买家名字
     */
    private String buyerName;
    /**
     * 买家电话
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 商品编号
     */
    private Integer phoneId;
    /**
     * 商品名称
     */
    private String phoneName;
    /**
     * 商品数量
     */
    private Integer phoneQuantity;
    /**
     * 商品小图
     */
    private String phoneCion;
    /**
     * 规格编号
     */
    private Integer specsId;
    /**
     * 规格名称
     */
    private String specsName;
    /**
     * 规格单价
     */
    private Double specsPrice;
    /**
     * 订单总金额
     */
    private Double orderAmount;
    /**
     * 支付状态 0-未支付，1-已支付
     */
    private Object payStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}