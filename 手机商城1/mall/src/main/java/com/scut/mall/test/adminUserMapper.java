package com.scut.mall.test;

import com.scut.mall.test.adminUser;
import com.scut.mall.test.adminUserExample;

public interface adminUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user
     *
     * @mbggenerated Sun Mar 08 18:37:02 CST 2020
     */
    int countByExample(adminUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user
     *
     * @mbggenerated Sun Mar 08 18:37:02 CST 2020
     */
    int insert(adminUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user
     *
     * @mbggenerated Sun Mar 08 18:37:02 CST 2020
     */
    int insertSelective(adminUser record);
}