package com.example.mybatisgeneratortest.mapper;

import com.example.mybatisgeneratortest.model.Wallet;
import java.util.List;

public interface WalletMapper {
    int deleteByPrimaryKey(String personid);

    int insert(Wallet record);

    Wallet selectByPrimaryKey(String personid);

    List<Wallet> selectAll();

    int updateByPrimaryKey(Wallet record);
}