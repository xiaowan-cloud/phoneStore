package com.scut.mall.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

/**
 * @ Author     ：Bin Liu
 * @ Date       ：2020/1/21 19:29
 * @ Description：
 * @ Modified By：
 */
@SpringBootTest
public class AddressDaoTest {
    @Autowired
    private AddressDao addressDao;

    @Test
    public void findByIdAndUserId() {
        int userId=1;
        System.out.println(addressDao.findByUserId(1));
    }

    @Test
    public void findAllByUserId() {
        System.out.println(addressDao.findByUserId(1));
    }

    @Test
    public void deleteById() {
        addressDao.deleteById(1);
    }
}