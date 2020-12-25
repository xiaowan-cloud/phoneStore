package com.phone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 购买者地址实体类
 *
 * @author makejava
 * @since 2020-12-22 14:31:37
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerAddress {

    private Integer addressId;
    /**
     * 买家名字
     */
    private String buyerName;
    /**
     * 买家密码
     */
    private String buyerPassword;
    /**
     * 买家电话
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 地址编码
     */
    private String areaCode;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;

}