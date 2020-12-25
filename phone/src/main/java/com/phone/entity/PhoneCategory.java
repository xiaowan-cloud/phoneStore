package com.phone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 手机种类实体类
 *
 * @author makejava
 * @since 2020-12-22 14:31:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneCategory {

    private Integer categoryId;
    /**
     * 类目名称
     */
    private String categoryName;
    /**
     * 类目编号
     */
    private Integer categoryType;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createName;


}