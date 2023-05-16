package com.xm.service;


import pojo.ProductDto;

import java.math.BigDecimal;

public interface IProductPacketService {

    String handOut_Product(ProductDto dto) throws Exception;




    BigDecimal rob_product(Integer userId, String packed) throws Exception;
}
