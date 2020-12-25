package com.phone.service;

import com.phone.entity.BuyerAddress;


public interface AddressService {
    /**
     * 查询buyerName地址
     * @param name
     * @return
     */
    BuyerAddress queryAddress(String name);

    /**
     * 修改地址
     * @param name
     * @param address
     * @return
     */
    void updateAddress(String name,String address);
}
