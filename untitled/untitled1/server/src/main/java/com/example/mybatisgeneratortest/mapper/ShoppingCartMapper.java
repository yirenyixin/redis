package com.example.mybatisgeneratortest.mapper;

import com.example.mybatisgeneratortest.model.ShoppingCart;
import java.util.List;

public interface ShoppingCartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Integer id);

    List<ShoppingCart> selectAll();

    int updateByPrimaryKey(ShoppingCart record);
}