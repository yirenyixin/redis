package jmu.dao;

import jmu.model.ProductState;

import java.util.List;

public interface IProductStateDAO {
    // 增加操作
    public void insert(ProductState productState) throws Exception ;

    public List<ProductState> queryAllmy(String id,String flag)throws Exception;

    public void update(ProductState productState)throws Exception;

    public List<ProductState> queryAllmytranslate(String id,String flag)throws Exception;

    public List<ProductState> queryAllmygoods(String id,String flag)throws Exception;

    public void delect(ProductState productState)throws Exception;

    public List<ProductState> queryorder1(String id)throws Exception;

    public List<ProductState> queryorder2(String id)throws Exception;

    public List<ProductState> queryorder3(String id)throws Exception;

    public List<ProductState> queryorder1_a(String id)throws Exception;

    public List<ProductState> queryorder2_a(String id)throws Exception;

    public List<ProductState> queryorder3_a(String id)throws Exception;

    void update_goods_state(String id, String state) throws Exception;

    void delect_goods(String id) throws Exception;

    public List<ProductState> queryAllIndent(String id, String flag1) throws Exception;

    List<ProductState> queryorderShop(String id);

    // 查询全部
//    public List<Product> queryAll(String flag) throws Exception ;
//
//    public List<Product> queryAll1(String id)throws Exception;
}
