package jmu.dao.impl;

import jmu.dao.IShopping_CartDAO;
import jmu.db.ConnectionManager;
import jmu.model.Shopping_Cart;
import jmu.utils.SQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Shopping_CartDAOImpl implements IShopping_CartDAO {

    @Override
    public int insert(Shopping_Cart shopping_cart) throws Exception {
//        String sql1 = "select id from shopping_cart order by id desc limit 1" ;
////        String sql1 ="SELECT revertID FROM revert where 1 order by revertID asc limit 1,1";
//
//
//
//        Connection conn1=null;
//        PreparedStatement pstmt1=null;
//       conn1= ConnectionManager.getConnection();
//        pstmt1=conn1.prepareStatement(sql1);
//        ResultSet rs = pstmt1.executeQuery(sql1);
//        String id = String.valueOf(0);
//        while (rs.next()) {
//            id=rs.getString(1);
//        }
//        int a= Integer.parseInt(id)+1;
//        shopping_cart.setId(a);
//        ConnectionManager.closeStatement(pstmt1);
//        ConnectionManager.closeConnection(conn1);

        SQLUtils sqlUtils=new SQLUtils();
        int a=sqlUtils.getID("select id from shopping_cart order by id desc limit 1");
        shopping_cart.setId(a++);
        String sql2 = "select productid from shopping_cart where buyerid=? " ;


        int flag=1;
        Connection conn2=null;
        PreparedStatement pstmt2=null;
        try {
            conn2 = ConnectionManager.getConnection();
            pstmt2 = conn2.prepareStatement(sql2);
            pstmt2.setString(1,shopping_cart.getBuyerid());
            ResultSet rs1 = pstmt2.executeQuery();
            while (rs1.next()){
                String x=rs1.getString(1);
                if(shopping_cart.getProductid().equals(x)){
                    flag=0;
                    break;
                }
            }
        } catch (Exception e) {
            throw new Exception("操作中出现错误！！！");
        } finally {
            ConnectionManager.closeStatement(pstmt2);
            ConnectionManager.closeConnection(conn2);
        }
        if(flag==1) {
            String sql = "INSERT INTO shopping_cart(productid,buyerid,id,shopid,num) VALUES(?,?,?,?,?)";
            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                conn = ConnectionManager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, shopping_cart.getProductid());
                pstmt.setString(2, shopping_cart.getBuyerid());
                pstmt.setInt(3, shopping_cart.getId());
                pstmt.setString(4, shopping_cart.getShopid());
                pstmt.setString(5,shopping_cart.getNum());
                pstmt.executeUpdate();
            } catch (Exception e) {
                // System.out.println(e) ;
                throw new Exception("操作中出现错误！！！");
            } finally {
                ConnectionManager.closeStatement(pstmt);
                ConnectionManager.closeConnection(conn);
            }
        }
        return flag;
    }

    @Override
    public List<Shopping_Cart> queryAll(String buyerid) throws Exception {
        List<Shopping_Cart> all=new ArrayList<Shopping_Cart>();
        String sql = "SELECT person.id,product.img,product.name,person.name,product.price,shopping_cart.id,shopping_cart.productid,shopping_cart.buyerid,shopping_cart.num,product.stock from person,product,shopping_cart" +
                " where shopping_cart.buyerid=? and shopping_cart.productid=product.id and shopping_cart.shopid=person.id ORDER BY person.name" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,buyerid) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                Shopping_Cart shopping_cart=new Shopping_Cart();
                shopping_cart.setShopid(rs.getString(1));
                shopping_cart.setImg(rs.getString(2));
                shopping_cart.setProductname(rs.getString(3));
                shopping_cart.setShopname(rs.getString(4));
                shopping_cart.setPrice(rs.getString(5));
                shopping_cart.setId(rs.getInt(6));
                shopping_cart.setProductid(rs.getString(7));
                shopping_cart.setNum(rs.getString(9));
                shopping_cart.setStock(rs.getString(10));
                all.add(shopping_cart);
            }
        }
        catch (Exception e)
        {
            System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
        return all;
    }

    @Override
    public void delect(Shopping_Cart shopping_cart) throws Exception {
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        sqlSession.delete("jmu.mapper.Shopping_CartMapper.delete",shopping_cart);
//        sqlSession.commit();	//提交事务
//        sqlSession.close();


        String sql = "DELETE FROM shopping_cart WHERE id=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;

        try {
            conn = ConnectionManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, shopping_cart.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("操作中出现错误！！！");
        } finally {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void delect1(String id) throws Exception {
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        sqlSession.delete("jmu.mapper.Shopping_CartMapper.delete",id);
//        sqlSession.commit();	//提交事务
//        sqlSession.close();


        String sql = "DELETE FROM shopping_cart WHERE id=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;

        try {
            conn = ConnectionManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("操作中出现错误！！！");
        } finally {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public List<Shopping_Cart> querycartbuyer(String id) throws Exception {
        String sql1 = "select count(id) FROM shopping_cart where buyerid=?" ;
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        List<Shopping_Cart> all=new ArrayList<Shopping_Cart>();
        try {
            conn1 = ConnectionManager.getConnection();
            pstmt1 = conn1.prepareStatement(sql1);
            pstmt1.setString(1,id);
            ResultSet rs = pstmt1.executeQuery();
            while (rs.next()){
                Shopping_Cart shopping_cart=new Shopping_Cart();
                shopping_cart.setId(rs.getInt(1));
                all.add(shopping_cart);
            }
        } catch (Exception e) {
            throw new Exception("操作中出现错误！！！");
        } finally {
            ConnectionManager.closeStatement(pstmt1);
            ConnectionManager.closeConnection(conn1);
        }
        return all;
    }
}
