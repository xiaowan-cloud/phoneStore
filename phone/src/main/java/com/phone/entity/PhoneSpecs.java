package com.phone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 手机规格实体类
 *
 * @author makejava
 * @since 2020-12-22 14:31:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneSpecs  {

    private Integer specsId;

    private String phoneId;
    /**
     * 规格名称
     */
    private String specsName;
    /**
     * 库存
     */
    private String specsStock;
    /**
     * 单价
     */
    private Double specsPrice;
    /**
     * 小图
     */
    private String specsIcon;
    /**
     * 预览图
     */
    private String specsPreview;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;

}