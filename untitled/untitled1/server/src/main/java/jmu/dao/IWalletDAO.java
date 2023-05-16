package jmu.dao;

import jmu.model.Wallet;

import java.util.List;

public interface IWalletDAO {
    public void insert(String id)throws Exception;

    public void update(Wallet wallet)throws Exception;

    public List<Wallet> getbalance(String my)throws Exception;

    public void reduce(Wallet wallet_buyer)throws Exception;

    public void add(Wallet wallet_seller)throws Exception;
}
