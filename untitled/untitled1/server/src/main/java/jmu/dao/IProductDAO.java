package jmu.dao;

import jmu.model.Product;

import java.util.List;

public interface IProductDAO {
    // 增加操作
    public void insert(Product product) throws Exception ;
    // 查询全部
    public List<Product> queryAll(String flag) throws Exception ;

    public List<Product> queryAll1(String id)throws Exception;

    public  List<Product> queryAllmy(String my)throws Exception;

    public List<Product> queryByLike(String cond) throws Exception ;

    public List<Product> querymyproduct(String id)throws Exception;

    public void update(Product product)throws Exception;

    public void delete(Product product)throws Exception;

    public void update_detail(Product product, int old_stock) throws Exception;
}
