package com.xm.mapper;

import jmu.model.Logistics;
import java.util.List;

public interface LogisticsMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(Logistics record);

    Logistics selectByPrimaryKey(Integer orderid);

    List<Logistics> selectAll();

    int updateByPrimaryKey(Logistics record);

    int getID();
}