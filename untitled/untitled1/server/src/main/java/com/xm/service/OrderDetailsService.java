package com.xm.service;

import com.xm.mapper.*;

import jmu.model.Address;
import jmu.model.OrderDetails;

import jmu.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@EnableAsync
public class OrderDetailsService implements IOrderDetailsService{

    private static final Logger log= LoggerFactory.getLogger(OrderDetailsService.class);

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private ProductMapper productMapper;



    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void insert(Integer userId, String packed, int orderid) {
        Address address=new Address();
        address.setUserid(String.valueOf(userId));
        address.setFlag(String.valueOf(1));
        address=addressMapper.getOne(address);
        Product product=new Product();
        product=productMapper.getOne(packed);
        OrderDetails orderDetails=new OrderDetails();
        orderDetails.setOrderid(orderid);
        orderDetails.setShopid(product.getShopid());
        orderDetails.setProductname(product.getName());
        orderDetails.setCity(address.getCity());
        orderDetails.setAddr(address.getAddr());
        orderDetails.setPhone(address.getPhone());
        orderDetails.setAddressee(address.getAddressee());
        orderDetailsMapper.insert(orderDetails);
    }
}









































