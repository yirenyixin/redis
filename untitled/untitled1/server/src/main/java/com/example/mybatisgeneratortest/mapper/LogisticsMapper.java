package com.example.mybatisgeneratortest.mapper;

import com.example.mybatisgeneratortest.model.Logistics;
import java.util.List;

public interface LogisticsMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(Logistics record);

    Logistics selectByPrimaryKey(Integer orderid);

    List<Logistics> selectAll();

    int updateByPrimaryKey(Logistics record);
}