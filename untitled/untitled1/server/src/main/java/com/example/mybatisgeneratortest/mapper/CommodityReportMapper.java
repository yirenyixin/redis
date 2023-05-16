package com.example.mybatisgeneratortest.mapper;

import com.example.mybatisgeneratortest.model.CommodityReport;
import java.util.List;

public interface CommodityReportMapper {
    int insert(CommodityReport record);

    List<CommodityReport> selectAll();
}