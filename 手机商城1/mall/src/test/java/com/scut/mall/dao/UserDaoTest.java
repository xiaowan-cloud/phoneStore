package com.scut.mall.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

/**
 * @ Author     ：Bin Liu
 * @ Date       ：2020/1/21 19:47
 * @ Description：${description}
 * @ Modified By：
 */
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void findByUserNameAndAndPassword() {
        System.out.println(userDao.findByUserNameAndAndPassword("admin","123456"));
    }

    @Test
    public void findByUserName() {
        System.out.println(userDao.findByUserName("admin"));
    }

    @Test
    @Ignore
    public void deleteById() {
        userDao.deleteById(1);
    }
}