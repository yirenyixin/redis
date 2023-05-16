package jmu.utils;

import com.xm.Redisson.Employer;
import com.xm.mapper.IndentMapper;
import com.xm.mapper.ProductDetailMapper;
import com.xm.mapper.ProductMapper;
import com.xm.mapper.WalletMapper;
import jmu.db.ConnectionManager;
import jmu.model.Indent;
import jmu.model.Product;
import jmu.model.ProductState;
import jmu.model.Wallet;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SQLUtils {
//    @Autowired
//    private ProductMapper productMapper;
//    @Autowired
//    private ProductDetailMapper productDetailMapper;
//    @Autowired
//    private IndentMapper indentMapper;
//    @Autowired
//    private WalletMapper walletMapper;
    public int getID(String sql) throws SQLException {

        Connection conn1=null;
        PreparedStatement pstmt1=null;
        conn1= ConnectionManager.getConnection();
        pstmt1=conn1.prepareStatement(sql);
        ResultSet rs = pstmt1.executeQuery(sql);
        String id = String.valueOf(0);
        int a=0;
        while (rs.next()) {
            id=rs.getString(1);
        }
        if(id==null){
            a=0;
        }
        else {
            a = Integer.parseInt(id) + 1;
        }
        ConnectionManager.closeStatement(pstmt1);
        ConnectionManager.closeConnection(conn1);
        return a;
    }

    public List getListID(String sql) throws SQLException {
        List a=new ArrayList();
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        conn1= ConnectionManager.getConnection();
        pstmt1=conn1.prepareStatement(sql);
        ResultSet rs = pstmt1.executeQuery(sql);

        while (rs.next()) {
            a.add(rs.getString(1));
        }
        ConnectionManager.closeStatement(pstmt1);
        ConnectionManager.closeConnection(conn1);
        return a;
    }

    public String getPacked(String sql) throws SQLException {
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        conn1= ConnectionManager.getConnection();
        pstmt1=conn1.prepareStatement(sql);
        ResultSet rs = pstmt1.executeQuery(sql);
        String packed = String.valueOf(0);
        while (rs.next()) {
            packed=rs.getString(1);
        }
        ConnectionManager.closeStatement(pstmt1);
        ConnectionManager.closeConnection(conn1);
        return packed;
    }

    public ProductState getproductState(String sql) throws SQLException {//
        ProductState productState=new ProductState();
        Connection conn=null;
        PreparedStatement pstmt=null;
        conn= ConnectionManager.getConnection();
        pstmt=conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery(sql);
        while (rs.next()) {
            productState.setCost(rs.getString(1));
            productState.setNum(rs.getString(2));
            productState.setBuyerid(rs.getString(3));
            productState.setSellerid(rs.getString(4));
            productState.setProductid(rs.getString(5));
        }
        ConnectionManager.closeStatement(pstmt);
        ConnectionManager.closeConnection(conn);
        return productState;
    }

    public void refund(ProductState productState) throws SQLException {//退款
        String sql="UPDATE wallet SET balance=balance+? WHERE personid=?";
        Connection conn=null;
        PreparedStatement pstmt=null;
        conn= ConnectionManager.getConnection();
        pstmt=conn.prepareStatement(sql);
        String x=productState.getCost();
        int y= Integer.parseInt(x);
        pstmt.setInt(1,y);
        pstmt.setString(2,productState.getBuyerid());
        pstmt.executeUpdate() ;

        sql="UPDATE wallet SET balance=balance-? WHERE personid=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,y);
        pstmt.setString(2,productState.getSellerid());
        pstmt.executeUpdate() ;

        ConnectionManager.closeStatement(pstmt);
        ConnectionManager.closeConnection(conn);
    }

    public void release(ProductState productState) throws SQLException {
        SQLUtils sqlUtils=new SQLUtils();
        List a=new ArrayList();
        a=sqlUtils.getListID("select id from product_detail where product_active=1 and user_id="+productState.getBuyerid());
        String sql="UPDATE product_detail SET product_active=0,user_id=NULL,rob_time=NULL WHERE id=?";
        Connection conn=null;
        PreparedStatement pstmt=null;
        conn= ConnectionManager.getConnection();
        pstmt=conn.prepareStatement(sql);
        int x= Integer.parseInt(productState.getNum());
        for (int i=0;i<x;i++) {
            String q= (String) a.get(i);
            pstmt.setInt(1, Integer.parseInt(q));
            pstmt.executeUpdate() ;
        }

        sql="UPDATE product SET stock=stock+? WHERE id=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,productState.getNum());
        pstmt.setInt(2, Integer.parseInt(productState.getProductid()));
        pstmt.executeUpdate() ;

        ConnectionManager.closeStatement(pstmt);
        ConnectionManager.closeConnection(conn);
    }

    public void delect(ProductState productState) throws SQLException {
        String sql="DELETE FROM logistics WHERE orderid=?";
        Connection conn=null;
        PreparedStatement pstmt=null;
        conn= ConnectionManager.getConnection();
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,productState.getId());
        pstmt.executeUpdate() ;

        sql="DELETE FROM order_1 WHERE id=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,productState.getId());
        pstmt.executeUpdate() ;

        sql="DELETE FROM order_details WHERE orderid=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,productState.getId());
        pstmt.executeUpdate() ;

        ConnectionManager.closeStatement(pstmt);
        ConnectionManager.closeConnection(conn);
    }

    public String getImg(String sql) throws SQLException {
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        conn1= ConnectionManager.getConnection();
        pstmt1=conn1.prepareStatement(sql);
        ResultSet rs = pstmt1.executeQuery();
        String a="";
        while (rs.next()) {
            a=rs.getString(1);
        }

        ConnectionManager.closeStatement(pstmt1);
        ConnectionManager.closeConnection(conn1);
        return a;
    }

    public String getShopid(String sql) throws SQLException {
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        conn1= ConnectionManager.getConnection();
        pstmt1=conn1.prepareStatement(sql);
        ResultSet rs = pstmt1.executeQuery();
        String id = "";
        while (rs.next()) {
            id=rs.getString(1);
        }
        ConnectionManager.closeStatement(pstmt1);
        ConnectionManager.closeConnection(conn1);
        return id;
    }


    public boolean redisson(Employer employer) throws SQLException, InterruptedException {
        int productid=Integer.parseInt(employer.getProduct_details_productID());
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        int id= Integer.parseInt(employer.getProduct_details_productID());
        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
        Indent indent=  sqlSession.selectOne("jmu.mapper.IndentMapper.selectproduct",id);
        sqlSession.commit();	//提交事务
        sqlSession.close();
//        System.out.println(indent.getAffirm());
        if(indent!=null){
            if(indent.getAffirm().equals("0")) {
                Product product = new Product();
                sqlSession = MyBatisUtils.getSession();//获取sqlSession
                product = sqlSession.selectOne("jmu.mapper.ProductMapper.selectpack", employer.getPacked());
                sqlSession.commit();    //提交事务
                if (product != null) {
                    Wallet shop = new Wallet();
                    Wallet buyer = new Wallet();
                    shop.setUserid(product.getShopid());
                    shop.setBalance(product.getPrice());
                    buyer.setUserid(employer.getUserid());
                    buyer.setBalance(product.getPrice());
                    sqlSession.update("jmu.mapper.WalletMapper.add", buyer);
                    sqlSession.commit();    //提交事务
                    sqlSession.update("jmu.mapper.WalletMapper.add", shop);
                    sqlSession.commit();    //提交事务

                    sqlSession.update("jmu.mapper.ProductMapper.addstock", employer.getPacked());
                    sqlSession.commit();    //提交事务

                    sqlSession.update("jmu.mapper.ProductDetailMapper.updateOne", id);
                    sqlSession.commit();    //提交事务

                    sqlSession.delete("jmu.mapper.IndentMapper.delect", id);
                    sqlSession.commit();    //提交事务
                    sqlSession.close();
                    return true;
                }
            }
        }

//        String sql="select affirm from indent where productid=? and affirm='0'";
//            conn1 = ConnectionManager.getConnection();
//            pstmt1 = conn1.prepareStatement(sql);
//            pstmt1.setInt(1, productid);
//            ResultSet rs = pstmt1.executeQuery();
//            Indent indent = new Indent();
//            while (rs.next()) {
//                indent.setAffirm(rs.getString(1));
//            }
//            if (indent.getAffirm() != null) {
//                if (indent.getAffirm().equals("0")) {
//                    Product product = new Product();
//                    sql = "    select   price,shopid  from product where produc_packed=?";
//                    pstmt1 = conn1.prepareStatement(sql);
//                    pstmt1.setString(1, employer.getPacked());
//                    rs = pstmt1.executeQuery();
//                    while (rs.next()) {
//                        product.setPrice(rs.getString(1));
//                        product.setShopid(rs.getString(2));
//                    }
//
//                    sql = "    update wallet set balance =balance + ? where personid = ?";
//                    pstmt1 = conn1.prepareStatement(sql);
//                    pstmt1.setString(1, product.getPrice());
//                    pstmt1.setString(2, employer.getUserid());
//                    pstmt1.executeUpdate();
//
//                    sql = "    update wallet set balance =balance - ? where personid = ?";
//                    pstmt1 = conn1.prepareStatement(sql);
//                    pstmt1.setString(1, product.getPrice());
//                    pstmt1.setString(2, product.getShopid());
//                    pstmt1.executeUpdate();
//
//                    sql = "update product set stock=stock+1 where produc_packed = ?";
//                    pstmt1 = conn1.prepareStatement(sql);
//                    pstmt1.setString(1, employer.getPacked());
//                    pstmt1.executeUpdate();
//
//                    sql = "    update product_detail set product_active = 0, user_id =null, rob_time =null where id =?";
//                    pstmt1 = conn1.prepareStatement(sql);
//                    pstmt1.setInt(1, productid);
//                    pstmt1.executeUpdate();
//
//                    sql = "    delete from indent where productid=?";
//                    pstmt1 = conn1.prepareStatement(sql);
//                    pstmt1.setInt(1, productid);
//                    pstmt1.executeUpdate();
//                    return true;
//                }
//            }
//        Indent indent=indentMapper.selectOne(Integer.parseInt(employer.getProduct_details_productID()));
//            if(indent!=null){
//                Product product=new Product();
//                Wallet shop=new Wallet();
//                Wallet buyer=new Wallet();
//                product=productMapper.getOne(employer.getPacked());
//                shop.setUserid(product.getShopid());
//                shop.setBalance(product.getPrice());
//                buyer.setUserid(employer.getUserid());
//                buyer.setBalance(product.getPrice());
//                productMapper.updateStock_add(employer.getPacked());
//                productDetailMapper.updateOne(Integer.parseInt(employer.getProduct_details_productID()));
//                walletMapper.add(buyer);
//                walletMapper.reduce(shop);
//                return true;
//            }
//            ConnectionManager.closeStatement(pstmt1);
//            ConnectionManager.closeConnection(conn1);
//        sqlSession.close();
        return false;
    }
}
