package com.phone.service.impl;

import com.phone.entity.BuyerAddress;
import com.phone.dao.AddressMapper;
import com.phone.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    
    @Override
    public BuyerAddress queryAddress(String name) {

        BuyerAddress buyerAddress = addressMapper.selectAddressByBuyerName(name);

        return buyerAddress;
    }

    @Override
    public void updateAddress(String name,String address) {

        BuyerAddress buyerAddress = BuyerAddress.builder().buyerName(name).buyerAddress(address).build();
        Integer res = addressMapper.updateAddressByBuyerName(buyerAddress);
        if (res == 0){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
