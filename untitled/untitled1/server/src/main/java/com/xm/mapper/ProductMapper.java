package com.xm.mapper;



import jmu.model.Product;

import java.util.List;

public interface ProductMapper {
    int insert(Product record);

    List<Product> selectAll();

    void updateStock(Product product);

    Product getOne(String packed);

    int getStock(String redId);

    int check(int id);

    String getCost(String redId);

    void updateStock_add(String packed);
}