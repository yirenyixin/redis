package com.xm.mapper;


import jmu.model.Wallet;

import java.util.List;

public interface WalletMapper {
    int deleteByPrimaryKey(String personid);

    int insert(Wallet record);

    Wallet selectByPrimaryKey(String personid);

    List<Wallet> selectAll();

    int updateByPrimaryKey(Wallet record);

    void add(Wallet shop);

    void reduce(Wallet buyer);
}