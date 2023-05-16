package com.xm.mapper;

import jmu.model.Indent;
import java.util.List;

public interface IndentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Indent record);

    Indent selectByPrimaryKey(Integer id);

    List<Indent> selectAll();

    int updateByPrimaryKey(Indent record);


    Indent selectOne(int parseInt);
}