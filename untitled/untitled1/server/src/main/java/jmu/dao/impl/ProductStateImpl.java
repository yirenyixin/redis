package jmu.dao.impl;

import jmu.dao.IProductStateDAO;
import jmu.db.ConnectionManager;
import jmu.utils.MyBatisUtils;
import jmu.model.ProductState;
import jmu.model.Shopping_Cart;
import org.apache.ibatis.session.SqlSession;
import jmu.utils.SQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductStateImpl implements IProductStateDAO {
    @Override
    public void delect(ProductState productState) throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
        Shopping_Cart shopping_cart=new Shopping_Cart();
        shopping_cart.setId(productState.getId());
        sqlSession.delete("jmu.mapper.Shopping_CartMapper.delete",shopping_cart);
        sqlSession.commit();	//提交事务
        sqlSession.close();
//        String sql = "DELETE FROM shopping_cart WHERE id=?" ;
//        Connection conn=null;
//        PreparedStatement pstmt=null;
//
//        try {
//            conn = ConnectionManager.getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, productState.getId());
//            pstmt.executeUpdate();
//        } catch (Exception e) {
//            throw new Exception("操作中出现错误！！！");
//        } finally {
//            ConnectionManager.closeStatement(pstmt);
//            ConnectionManager.closeConnection(conn);
//        }
    }
    @Override
    public void update(ProductState productState)throws Exception {
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        sqlSession.update("jmu.mapper.ProductStateMapper.update",productState);
//        sqlSession.commit();	//提交事务
//        sqlSession.close();

        String sql = "update logistics set state=? where orderid=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
//            pstmt.setString(1,product.getId()) ;
            pstmt.setString(1,productState.getState());
            pstmt.setInt(2,productState.getId());
            pstmt.executeUpdate() ;
        }
        catch (Exception e)
        {
            // System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void insert(ProductState productState) throws Exception {
        SqlSession sqlSession1 =MyBatisUtils.getSession();
        int id=sqlSession1.selectOne("jmu.mapper.ProductStateMapper.getID");
        sqlSession1.commit();
        sqlSession1.close();
        id++;
        productState.setId(id);

        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date currentTime = new java.util.Date();//得到当前系统时间
        String str_date1 = formatter.format(currentTime); //将日期时间格式化
        String yy = str_date1.substring(0,4);
        String mm = str_date1.substring(5,7);
        String dd = str_date1.substring(8,10);
        productState.setDate(yy+"-"+mm+"-"+dd);

        String sql = "INSERT INTO order_1(id,buyerid,sellerid,productid,num,cost,date) VALUES(?,?,?,?,?,?,?)" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,productState.getId());
            pstmt.setString(2,productState.getBuyerid());
            pstmt.setString(3,productState.getSellerid());
            pstmt.setString(4,productState.getProductid());
            pstmt.setString(5,productState.getNum());
            pstmt.setString(6,productState.getCost());
            pstmt.setString(7,productState.getDate());
            pstmt.executeUpdate() ;
        }
        catch (Exception e)
        {
            // System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
        //订单信息
        sql = "INSERT INTO order_details(orderid,shopid,productname,city,addr,phone,addressee) VALUES(?,?,?,?,?,?,?)" ;
         conn=null;
         pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,productState.getId());
            pstmt.setString(2,productState.getSellerid());
            pstmt.setString(3,productState.getProductname());
            pstmt.setString(4,productState.getCity());
            pstmt.setString(5,productState.getAddr());
            pstmt.setString(6,productState.getPhone());
            pstmt.setString(7,productState.getAddressee());
            pstmt.executeUpdate() ;
        }
        catch (Exception e)
        {
            // System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
        //物流
        sql = "INSERT INTO logistics(orderid,state,return_goods ) VALUES(?,?,?)" ;
        conn=null;
        pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,productState.getId());
            pstmt.setString(2,"已下单");
            pstmt.setString(3,productState.getReturn_goods());
            pstmt.executeUpdate() ;
        }
        catch (Exception e)
        {
            // System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
        //减库存
        sql = "UPDATE product set stock=stock-? where id=?" ;
        conn=null;
        pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,productState.getNum());
            pstmt.setInt(2,Integer.valueOf(productState.getProductid()));
            pstmt.executeUpdate() ;

            sql = "UPDATE product_detail set product_active=0,user_id=?,rob_time=?,product_active=1 where id=?";
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            int num=Integer.valueOf(productState.getNum());
            SQLUtils sqlUtils=new SQLUtils();
            List a=new ArrayList();
            a=sqlUtils.getListID("select id from product_detail WHERE product_id="+productState.getProductid()+" and product_active=0");
            Date date=new Date();
            for(int i=0;i<num;i++){
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
                pstmt.setString(2,dateFormat.format(date));
                pstmt.setLong(1, Integer.parseInt(productState.getBuyerid()));
                String q= (String) a.get(i);
                int w= Integer.parseInt(q);
                pstmt.setInt(3,w);
                pstmt.executeUpdate() ;
            }
        }
        catch (Exception e)
        {
            // System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }

    }

    @Override
    public List<ProductState> queryAllmy(String id,String flag) throws Exception {//待收货
        List<ProductState> all = new ArrayList<ProductState>() ;
        String sql=null;
        SQLUtils sqlUtils=new SQLUtils();
        if(flag.equals("0")) {
            sql = "select indent.id, indent.buyerid, indent.shopid, indent.state, indent.productid," +
                    " indent.num, indent.cost, indent.date, indent.city, indent.addr, indent.phone," +
                    "indent.addressee,indent.back,indent.affirm,product.name,person.name,product.img" +
                    " from indent,product,person where buyerid =? and affirm=1 and buyerid=person.id and indent.pack=product.produc_packed";
        }else {
            sql = "select indent.id, indent.buyerid, indent.shopid, indent.state, indent.productid," +
                    " indent.num, indent.cost, indent.date, indent.city, indent.addr, indent.phone," +
                    "indent.addressee,indent.back,indent.affirm,product.name,person.name,product.img" +
                    " from indent,product,person where indent.shopid =? and affirm=1 and indent.shopid=person.id and indent.pack=product.produc_packed";
        }
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                ProductState productState=new ProductState();
                productState.setProductname(rs.getString(15));
                productState.setBuyername(rs.getString(16));
                productState.setState(rs.getString(4));
                productState.setCity(rs.getString(9));
                productState.setAddr(rs.getString(10));
                productState.setPhone(rs.getString(11));
                productState.setAddressee(rs.getString(12));
                productState.setNum(rs.getString(6));
                productState.setCost(rs.getString(7));
                productState.setId(rs.getInt(1));
                productState.setReturn_goods(rs.getString(11));
                productState.setSellerid(rs.getString(12));
                productState.setProductid(rs.getString(5));
//                String img=sqlUtils.getImg("select img from product where id="+productState.getProductid());
                productState.setImg(rs.getString(17));
                all.add(productState);
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
        return all ;
    }


    @Override
    public List<ProductState> queryAllmytranslate(String id,String flag) throws Exception {//带收货
        List<ProductState> all = new ArrayList<ProductState>() ;
        String sql=null;
        if(flag.equals("0")) {
            sql = "SELECT order_details.productname,person.name,logistics.state,order_details.city," +
                    "order_details.addr,order_details.phone,order_details.addressee,order_1.num,order_1.cost,order_1.id,logistics.return_goods " +
                    "from logistics,person,order_details,order_1 where order_1.id=order_details.orderid " +
                    "and order_1.id=logistics.orderid and order_1.buyerid=person.id and person.id=? and state='已送达' ";
        }
        else{
            sql = "SELECT order_details.productname,person.name,logistics.state,order_details.city," +
                    "order_details.addr,order_details.phone,order_details.addressee,order_1.num,order_1.cost,order_1.id,logistics.return_goods " +
                    "from logistics,person,order_details,order_1 where order_1.id=order_details.orderid " +
                    "and order_1.id=logistics.orderid and order_1.sellerid=person.id and person.id=? and state='已送达' ";
        }
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                ProductState productState=new ProductState();
                productState.setProductname(rs.getString(1));
                productState.setBuyername(rs.getString(2));
                productState.setState(rs.getString(3));
                productState.setCity(rs.getString(4));
                productState.setAddr(rs.getString(5));
                productState.setPhone(rs.getString(6));
                productState.setAddressee(rs.getString(7));
                productState.setNum(rs.getString(8));
                productState.setCost(rs.getString(9));
                productState.setId(rs.getInt(10));
                productState.setReturn_goods(rs.getString(11));
                all.add(productState);
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
        return all ;
    }

    @Override
    public List<ProductState> queryAllmygoods(String id,String flag) throws Exception {//已收货
        List<ProductState> all = new ArrayList<ProductState>() ;
        String sql=null;
        if(flag.equals("0")) {
            sql = "SELECT order_details.productname,person.name,logistics.state,order_details.city," +
                    "order_details.addr,order_details.phone,order_details.addressee,order_1.num,order_1.cost,order_1.id,logistics.return_goods " +
                    "from logistics,person,order_details,order_1 where order_1.id=order_details.orderid " +
                    "and order_1.id=logistics.orderid and order_1.buyerid=person.id and person.id=? and state='已收货' ";
        }
        else{
            sql = "SELECT order_details.productname,person.name,logistics.state,order_details.city," +
                    "order_details.addr,order_details.phone,order_details.addressee,order_1.num,order_1.cost,order_1.id,logistics.return_goods " +
                    "from logistics,person,order_details,order_1 where order_1.id=order_details.orderid " +
                    "and order_1.id=logistics.orderid and order_1.sellerid=person.id and person.id=? and state='已收货' ";
        }
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                ProductState productState=new ProductState();
                productState.setProductname(rs.getString(1));
                productState.setBuyername(rs.getString(2));
                productState.setState(rs.getString(3));
                productState.setCity(rs.getString(4));
                productState.setAddr(rs.getString(5));
                productState.setPhone(rs.getString(6));
                productState.setAddressee(rs.getString(7));
                productState.setNum(rs.getString(8));
                productState.setCost(rs.getString(9));
                productState.setId(rs.getInt(10));
                productState.setReturn_goods(rs.getString(11));
                all.add(productState);
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
        return all ;
    }



    @Override
    public List<ProductState> queryorder1(String id) throws Exception {
        List<ProductState> all = new ArrayList<ProductState>() ;
        String sql=null;
        SQLUtils sqlUtils=new SQLUtils();
            sql = "select indent.id, indent.buyerid, indent.shopid, indent.state, indent.productid," +
                    " indent.num, indent.cost, indent.date, indent.city, indent.addr, indent.phone," +
                    "indent.addressee,indent.back,indent.affirm,product.name,person.name,product.img" +
                    " from indent,product,person where buyerid =? and affirm=1 and buyerid=person.id and indent.pack=product.produc_packed";

//            sql = "select indent.id, indent.buyerid, indent.shopid, indent.state, indent.productid," +
//                    " indent.num, indent.cost, indent.date, indent.city, indent.addr, indent.phone," +
//                    "indent.addressee,indent.back,indent.affirm,product.name,person.name,product.img" +
//                    " from indent,product,person where indent.shopid =? and affirm=1 and indent.shopid=person.id and indent.pack=product.produc_packed";
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                ProductState productState=new ProductState();
                productState.setProductname(rs.getString(15));
                productState.setBuyername(rs.getString(16));
                productState.setState(rs.getString(4));
                productState.setCity(rs.getString(9));
                productState.setAddr(rs.getString(10));
                productState.setPhone(rs.getString(11));
                productState.setAddressee(rs.getString(12));
                productState.setNum(rs.getString(6));
                productState.setCost(rs.getString(7));
                productState.setId(rs.getInt(1));
                productState.setReturn_goods(rs.getString(11));
                productState.setSellerid(rs.getString(12));
                productState.setProductid(rs.getString(5));
//                String img=sqlUtils.getImg("select img from product where id="+productState.getProductid());
                productState.setImg(rs.getString(17));
                all.add(productState);
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
        return all ;
    }

    @Override
    public List<ProductState> queryorder2(String id) throws Exception {
        String sql1 = "select count(id) FROM order_1,logistics where buyerid=? and logistics.state='已送达' and order_1.id=logistics.orderid" ;
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        List<ProductState> all=new ArrayList<ProductState>();
        try {
            conn1 = ConnectionManager.getConnection();
            pstmt1 = conn1.prepareStatement(sql1);
            pstmt1.setString(1, id);
            ResultSet rs = pstmt1.executeQuery();
            while (rs.next()){
                ProductState productState=new ProductState();
                productState.setId(rs.getInt(1));
                all.add(productState);
            }
        } catch (Exception e) {
            throw new Exception("操作中出现错误！！！");
        } finally {
            ConnectionManager.closeStatement(pstmt1);
            ConnectionManager.closeConnection(conn1);
        }
        return all;
    }

    @Override
    public List<ProductState> queryorder3(String id) throws Exception {
        String sql1 = "select count(id) FROM order_1,logistics where buyerid=? and logistics.state='已收货' and order_1.id=logistics.orderid" ;
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        List<ProductState> all=new ArrayList<ProductState>();
        try {
            conn1 = ConnectionManager.getConnection();
            pstmt1 = conn1.prepareStatement(sql1);
            pstmt1.setString(1, id);
            ResultSet rs = pstmt1.executeQuery();
            while (rs.next()){
                ProductState productState=new ProductState();
                productState.setId(rs.getInt(1));
                all.add(productState);
            }
        } catch (Exception e) {
            throw new Exception("操作中出现错误！！！");
        } finally {
            ConnectionManager.closeStatement(pstmt1);
            ConnectionManager.closeConnection(conn1);
        }
        return all;
    }

    @Override
    public List<ProductState> queryorder1_a(String id) throws Exception {
        List<ProductState> all = new ArrayList<ProductState>() ;
        String sql=null;
        SQLUtils sqlUtils=new SQLUtils();
//        sql = "select indent.id, indent.buyerid, indent.shopid, indent.state, indent.productid," +
//                " indent.num, indent.cost, indent.date, indent.city, indent.addr, indent.phone," +
//                "indent.addressee,indent.back,indent.affirm,product.name,person.name,product.img" +
//                " from indent,product,person where buyerid =? and affirm=1 and buyerid=person.id and indent.pack=product.produc_packed";

            sql = "select indent.id, indent.buyerid, indent.shopid, indent.state, indent.productid," +
                    " indent.num, indent.cost, indent.date, indent.city, indent.addr, indent.phone," +
                    "indent.addressee,indent.back,indent.affirm,product.name,person.name,product.img" +
                    " from indent,product,person where indent.shopid =? and affirm=1 and indent.shopid=person.id and indent.pack=product.produc_packed";
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                ProductState productState=new ProductState();
                productState.setProductname(rs.getString(15));
                productState.setBuyername(rs.getString(16));
                productState.setState(rs.getString(4));
                productState.setCity(rs.getString(9));
                productState.setAddr(rs.getString(10));
                productState.setPhone(rs.getString(11));
                productState.setAddressee(rs.getString(12));
                productState.setNum(rs.getString(6));
                productState.setCost(rs.getString(7));
                productState.setId(rs.getInt(1));
                productState.setReturn_goods(rs.getString(11));
                productState.setSellerid(rs.getString(12));
                productState.setProductid(rs.getString(5));
//                String img=sqlUtils.getImg("select img from product where id="+productState.getProductid());
                productState.setImg(rs.getString(17));
                all.add(productState);
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
        return all ;
    }

    @Override
    public List<ProductState> queryorder2_a(String id) throws Exception {
        String sql1 = "select count(id) FROM order_1,logistics where sellerid=? and logistics.state='已送达' and order_1.id=logistics.orderid" ;
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        List<ProductState> all=new ArrayList<ProductState>();
        try {
            conn1 = ConnectionManager.getConnection();
            pstmt1 = conn1.prepareStatement(sql1);
            pstmt1.setString(1, id);
            ResultSet rs = pstmt1.executeQuery();
            while (rs.next()){
                ProductState productState=new ProductState();
                productState.setId(rs.getInt(1));
                all.add(productState);
            }
        } catch (Exception e) {
            throw new Exception("操作中出现错误！！！");
        } finally {
            ConnectionManager.closeStatement(pstmt1);
            ConnectionManager.closeConnection(conn1);
        }
        return all;
    }

    @Override
    public List<ProductState> queryorder3_a(String id) throws Exception {
        String sql1 = "select count(id) FROM order_1,logistics where sellerid=? and logistics.state='已收货' and order_1.id=logistics.orderid" ;
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        List<ProductState> all=new ArrayList<ProductState>();
        try {
            conn1 = ConnectionManager.getConnection();
            pstmt1 = conn1.prepareStatement(sql1);
            pstmt1.setString(1, id);
            ResultSet rs = pstmt1.executeQuery();
            while (rs.next()){
                ProductState productState=new ProductState();
                productState.setId(rs.getInt(1));
                all.add(productState);
            }
        } catch (Exception e) {
            throw new Exception("操作中出现错误！！！");
        } finally {
            ConnectionManager.closeStatement(pstmt1);
            ConnectionManager.closeConnection(conn1);
        }
        return all;
    }

    @Override
    public void update_goods_state(String id, String state) throws Exception {
        String sql1 = "UPDATE logistics set return_goods=? where orderid=?" ;
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        try {
            conn1 = ConnectionManager.getConnection();
            pstmt1 = conn1.prepareStatement(sql1);
            pstmt1.setString(1, state);
            pstmt1.setInt(2, Integer.parseInt(id));
            pstmt1.executeUpdate() ;
        } catch (Exception e) {
            throw new Exception("操作中出现错误！！！");
        } finally {
            ConnectionManager.closeStatement(pstmt1);
            ConnectionManager.closeConnection(conn1);
        }
    }

    @Override
    public void delect_goods(String id) throws Exception {
        SQLUtils sqlUtils=new SQLUtils();
        ProductState productState=new ProductState();
        productState=sqlUtils.getproductState("select cost,num,buyerid,sellerid,productid from order_1 where id="+id);
        productState.setId(Integer.parseInt(id));
        sqlUtils.refund(productState);//退款
        sqlUtils.release(productState);//释放锁以及添加库存
        sqlUtils.delect(productState);//删除记录
    }

    @Override
    public List<ProductState> queryAllIndent(String id, String flag1) throws Exception {
        List<ProductState> all = new ArrayList<ProductState>() ;
        String sql=null;
        SQLUtils sqlUtils=new SQLUtils();
//        sql = "SELECT order_details.productname,person.name,logistics.state,order_details.city," +
//               "order_details.addr,order_details.phone,order_details.addressee,order_1.num,order_1.cost,"+
//                "order_1.id,logistics.return_goods,order_details.shopid " +
//                ",order_1.productid from logistics,person,order_details,order_1 where order_1.id=order_details.orderid " +
//                "and order_1.id=logistics.orderid and order_1.buyerid=person.id and person.id=? and state='已下单' ";
        sql="select indent.id, indent.buyerid, indent.shopid, indent.state, indent.productid,"+
                " indent.num, indent.cost, indent.date, indent.city, indent.addr, indent.phone,"+
                "indent.addressee,indent.back,indent.affirm,product.name,person.name,product.img"+
                " from indent,product,person where buyerid =? and affirm=0 and buyerid=person.id and indent.pack=product.produc_packed";
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                ProductState productState=new ProductState();
                productState.setProductname(rs.getString(15));
                productState.setBuyername(rs.getString(16));
                productState.setState(rs.getString(4));
                productState.setCity(rs.getString(9));
                productState.setAddr(rs.getString(10));
                productState.setPhone(rs.getString(11));
                productState.setAddressee(rs.getString(12));
                productState.setNum(rs.getString(6));
                productState.setCost(rs.getString(7));
                productState.setId(rs.getInt(1));
                productState.setReturn_goods(rs.getString(11));
                productState.setSellerid(rs.getString(12));
                productState.setProductid(rs.getString(5));
//                String img=sqlUtils.getImg("select img from product where id="+productState.getProductid());
                productState.setImg(rs.getString(17));
                all.add(productState);
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
        return all ;
    }

    @Override
    public List<ProductState> queryorderShop(String id) {
        List<ProductState> all = new ArrayList<ProductState>() ;
        String sql=null;
        SQLUtils sqlUtils=new SQLUtils();
//        sql = "select indent.id, indent.buyerid, indent.shopid, indent.state, indent.productid," +
//                " indent.num, indent.cost, indent.date, indent.city, indent.addr, indent.phone," +
//                "indent.addressee,indent.back,indent.affirm,product.name,person.name,product.img" +
//                " from indent,product,person where buyerid =? and affirm=1 and buyerid=person.id and indent.pack=product.produc_packed";

            sql = "select indent.id, indent.buyerid, indent.shopid, indent.state, indent.productid," +
                    " indent.num, indent.cost, indent.date, indent.city, indent.addr, indent.phone," +
                    "indent.addressee,indent.back,indent.affirm,product.name,person.name,product.img" +
                    " from indent,product,person where indent.shopid =? and affirm=1 and indent.shopid=person.id and indent.pack=product.produc_packed";
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                ProductState productState=new ProductState();
                productState.setProductname(rs.getString(15));
                productState.setBuyername(rs.getString(16));
                productState.setState(rs.getString(4));
                productState.setCity(rs.getString(9));
                productState.setAddr(rs.getString(10));
                productState.setPhone(rs.getString(11));
                productState.setAddressee(rs.getString(12));
                productState.setNum(rs.getString(6));
                productState.setCost(rs.getString(7));
                productState.setId(rs.getInt(1));
                productState.setReturn_goods(rs.getString(11));
                productState.setSellerid(rs.getString(12));
                productState.setProductid(rs.getString(5));
//                String img=sqlUtils.getImg("select img from product where id="+productState.getProductid());
                productState.setImg(rs.getString(17));
                all.add(productState);
            }
        }
        catch (Exception e)
        {
            System.out.println(e) ;
            try {
                throw new Exception("操作中出现错误！！！") ;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        finally
        {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
        return all ;
    }

}
