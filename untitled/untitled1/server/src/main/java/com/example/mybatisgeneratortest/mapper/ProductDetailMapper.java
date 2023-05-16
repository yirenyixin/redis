package com.example.mybatisgeneratortest.mapper;

import com.example.mybatisgeneratortest.model.ProductDetail;
import java.util.List;

public interface ProductDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductDetail record);

    ProductDetail selectByPrimaryKey(Integer id);

    List<ProductDetail> selectAll();

    int updateByPrimaryKey(ProductDetail record);
}