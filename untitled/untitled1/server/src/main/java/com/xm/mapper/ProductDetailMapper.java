package com.xm.mapper;


import jmu.model.ProductDetail;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public interface ProductDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductDetail record);

    ProductDetail selectByPrimaryKey(Integer id);

    List<ProductDetail> selectAll();

    int updateByPrimaryKey(ProductDetail record);

    void updateOne(int product_details_productID);
}