package com.example.mybatisgeneratortest.mapper;

import com.example.mybatisgeneratortest.model.Person;
import java.util.List;

public interface PersonMapper {
    int insert(Person record);

    List<Person> selectAll();
}