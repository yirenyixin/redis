package com.xm.service;

import com.xm.mapper.*;
import jmu.model.Order1;

import jmu.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class Order1Service implements IOrder1Service {

    private static final Logger log = LoggerFactory.getLogger(Order1Service.class);
    @Autowired
    private Order1Mapper order1Mapper;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public void insert(Integer userId, String packed, int orderid) {
        Order1 order1=new Order1();
        Product product=new Product();
        product=productMapper.getOne(packed);
        order1.setId(orderid);
        order1.setSellerid(product.getShopid());
        order1.setBuyerid(String.valueOf(userId));
        order1.setProductid(String.valueOf(product.getId()));
        order1.setNum(String.valueOf(1));
        Date date=new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        order1.setDate(dateFormat.format(date));
        order1.setCost(product.getPrice());
        order1Mapper.insert(order1);

    }
}






































