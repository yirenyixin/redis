package com.xm.service;


import jmu.model.Product;
import org.springframework.scheduling.annotation.Async;
import pojo.ProductDto;

import java.math.BigDecimal;
import java.sql.SQLException;

//红包记录服务

public interface IProductService {



    @Async
    void recordProduct(ProductDto dto, String shopId) throws SQLException;

    @Async
    void recordRobProductPacket(Integer userId, String redId, BigDecimal amount) throws Exception;

    int getNum(String redId);

    int check(String toString);

    String getCost(String redId);

    Product getOne(String redId);
}
