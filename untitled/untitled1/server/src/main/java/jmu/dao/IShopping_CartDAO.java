package jmu.dao;

import jmu.model.Shopping_Cart;

import java.util.List;

public interface IShopping_CartDAO {
    public int insert(Shopping_Cart shopping_cart)throws Exception;

    public List<Shopping_Cart> queryAll(String buyerid)throws Exception;

    public void delect(Shopping_Cart shopping_cart)throws Exception;

    public void delect1(String id)throws Exception;

    public List<Shopping_Cart> querycartbuyer(String id)throws Exception;
}
