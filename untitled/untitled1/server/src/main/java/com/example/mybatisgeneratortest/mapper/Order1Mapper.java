package com.example.mybatisgeneratortest.mapper;

import com.example.mybatisgeneratortest.model.Order1;
import java.util.List;

public interface Order1Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order1 record);

    Order1 selectByPrimaryKey(Integer id);

    List<Order1> selectAll();

    int updateByPrimaryKey(Order1 record);
}