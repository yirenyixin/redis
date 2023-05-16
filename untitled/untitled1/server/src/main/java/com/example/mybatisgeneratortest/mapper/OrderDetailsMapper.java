package com.example.mybatisgeneratortest.mapper;

import com.example.mybatisgeneratortest.model.OrderDetails;
import java.util.List;

public interface OrderDetailsMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(OrderDetails record);

    OrderDetails selectByPrimaryKey(Integer orderid);

    List<OrderDetails> selectAll();

    int updateByPrimaryKey(OrderDetails record);
}