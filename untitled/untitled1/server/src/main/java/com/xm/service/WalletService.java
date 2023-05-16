package com.xm.service;

import com.xm.mapper.ProductMapper;
import com.xm.mapper.WalletMapper;

import com.xm.rabbitmq.hanOut_Fanout;
import jmu.model.Product;
import jmu.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService implements IWalletService{
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private WalletMapper walletMapper;


    @Override
    public synchronized boolean update(Integer userId, String packed, String value, int flag) {
        Wallet wallet=new Wallet();
        Product product=new Product();
        product=productMapper.getOne(packed);
        Wallet buyer=new Wallet();
        Wallet shop=new Wallet();
        buyer.setPersonid(String.valueOf(userId));
        buyer.setBalance(product.getPrice());
        shop.setPersonid(product.getShopid());
        shop.setBalance(product.getPrice());
        wallet=walletMapper.selectByPrimaryKey(buyer.getPersonid());
            if (Integer.valueOf(wallet.getBalance()) > Integer.valueOf(product.getPrice()) && flag == 1) {
                WalletService walletService = new WalletService();
                walletMapper.add(shop);
                walletMapper.reduce(buyer);
                return true;
            }
            if (flag == 0) {
                try {
                    walletMapper.add(buyer);
                    walletMapper.reduce(shop);
                    hanOut_Fanout hanOut_fanout = new hanOut_Fanout();
                    hanOut_fanout.refund("退款成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
            return false;
    }
}
