package com.phone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 手机详情实体类
 *
 * @author makejava
 * @since 2020-12-22 14:31:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDetails {

    private Integer phoneId;
    /**
     * 商品名称
     */
    private String phoneName;
    /**
     * 商品单价
     */
    private Double phonePrice;
    /**
     * 描述
     */
    private String phoneDescription;
    /**
     * 库存
     */
    private Integer phoneStock;
    /**
     * 小图
     */
    private String phoneIcon;
    /**
     * 类目编号
     */
    private Integer categoryType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 标签
     */
    private String phoneTag;

}