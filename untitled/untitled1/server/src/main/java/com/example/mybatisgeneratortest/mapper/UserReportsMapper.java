package com.example.mybatisgeneratortest.mapper;

import com.example.mybatisgeneratortest.model.UserReports;
import java.util.List;

public interface UserReportsMapper {
    int insert(UserReports record);

    List<UserReports> selectAll();
}