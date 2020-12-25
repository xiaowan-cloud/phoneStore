package com.phone.Api;

import com.phone.entity.BuyerAddress;
import com.phone.service.AddressService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Api("地址相关Api")
@RequestMapping("/address")
@Produces(MediaType.APPLICATION_JSON)
@RestController
public class AddressApi {

    @Autowired
    private AddressService addressService;

    @ApiOperation("查询购买者地址")
    @ApiImplicitParam(name = "name",value = "购买者名字")
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public BuyerAddress queryBuyerAddress(@Param("name") String name){
        BuyerAddress buyerAddress = addressService.queryAddress(name);
        return buyerAddress;
    }

    @ApiOperation("修改购买者地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "购买者名字"),
            @ApiImplicitParam(name = "address",value = "购买者地址")
    })
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void updateBuyerAddress(@Param("name") String name,@Param("address") String address){
        addressService.updateAddress(name,address);
    }

}
