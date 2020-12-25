package com.phone.dao;

import com.phone.entity.BuyerAddress;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface AddressMapper {

    BuyerAddress selectAddressByBuyerName(String buyerAddress);

    Integer updateAddressByBuyerName(BuyerAddress buyerAddress);
}
