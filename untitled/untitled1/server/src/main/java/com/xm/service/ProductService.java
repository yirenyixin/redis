package com.xm.service;

//import com.xm.rabbitmq.hanOut_Fanout;

import com.xm.mapper.*;
import jmu.model.Product;
import jmu.model.ProductDetail;
import pojo.*;
//import com.xm.mapper.RedDetailMapper;
//import com.xm.mapper.RedRecordMapper;
//import com.xm.mapper.RedRobRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jmu.utils.SQLUtils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;


@Service
@EnableAsync
public class ProductService implements IProductService {

    private static final Logger log= LoggerFactory.getLogger(ProductService.class);


    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDetailMapper productDetailMapper;
    @Autowired
    private LogisticsMapper logisticsMapper;
    @Autowired
    private Order1Mapper order1Mapper;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;





    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void recordProduct(ProductDto dto, String pack) throws SQLException {//发布商品
        Product product= new Product();
        SQLUtils sqlUtils=new SQLUtils();
        int a= sqlUtils.getID("select id from product order by id desc limit 1");//获取id
        product.setId(a);
        product.setStock(dto.getStock());
        product.setPrice(dto.getPrice());
        product.setShopid(dto.getShopId());
        product.setName(dto.getProductName());
        product.setImg(dto.getImg());
        product.setSize(dto.getSize());
        product.setType(dto.getType());
        product.setColor(dto.getColor());
        product.setProduc_packed(pack);
        productMapper.insert(product);//插入
        ProductDetail productDetail=new ProductDetail();
        productDetail.setProduct_id(a);
        productDetail.setProduct_active(0);
        productDetail.setProduct_packed(pack);
        a= sqlUtils.getID("select id from product_detail order by id desc limit 1");//取id
        for(int i=0;i<Integer.valueOf(dto.getStock());i++) {
            productDetail.setId(a++);
            productDetail.setCreat_time(new Date());
             productDetailMapper.insert(productDetail);//插入
        }
    }

    @Override
    @Async
    public void recordRobProductPacket(Integer userId, String packed, BigDecimal amount) throws Exception {
        ProductDetail productDetail=new ProductDetail();
        productDetail.setId(Integer.valueOf(String.valueOf(amount)));
        productDetail.setUser_id(Long.valueOf(userId));
        productDetail.setRob_time(new Date());
        productDetail.setProduct_active(1);
        productDetailMapper.updateByPrimaryKey(productDetail);
        Product product=new Product();
        product.setProduc_packed(packed);
        productMapper.updateStock(product);
    }
    @Override
    public int getNum(String redId) {

        int a=productMapper.getStock(redId);
        return a;
    }

    @Override
    public int check(String toString) {
        int id= Integer.parseInt(toString);
        int a=productMapper.check(id);
        return a;
    }

    @Override
    public String getCost(String redId) {
        String a=productMapper.getCost(redId);
        return a;
    }

    @Override
    public Product getOne(String redId) {
        Product product=new Product();
        product=productMapper.getOne(redId);
        return product;
    }
}









































