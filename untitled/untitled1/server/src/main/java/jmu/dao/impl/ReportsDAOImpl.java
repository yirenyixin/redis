package jmu.dao.impl;

import jmu.dao.IReportsDAO;
import jmu.db.ConnectionManager;
import jmu.model.Reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportsDAOImpl implements IReportsDAO {
    @Override
    public List<Reports> report(String id, String flag) throws Exception {
        List<Reports> all = new ArrayList<Reports>() ;
        String sql=null;
        String sql1=null;
        String sql0=null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        if(flag.equals("0")) {
            String id1="b";
            id1=id1+id;
             sql = "SELECT order_1.sellerid,sum(order_1.cost),person.name FROM order_1," +
                     "person where order_1.buyerid=? AND order_1.sellerid=person.id and order_1.date>=? group by order_1.sellerid";
             sql0="DROP VIEW IF EXISTS "+id1;
             sql1="CREATE VIEW "+id1+" AS SELECT order_1.sellerid,sum(order_1.cost),person.name FROM order_1," +
                     "person where order_1.buyerid=? AND order_1.sellerid=person.id and order_1.date>=? group by order_1.sellerid";
        }
        else{
            String id1="s";
            id1=id1+id;
            sql = "SELECT order_1.productid ,sum(order_1.cost),product.name,sum(order_1.num) FROM order_1,product" +
                    " where order_1.sellerid=? AND order_1.productid=product.id and order_1.date>? group by order_1.productid";
            sql0="DROP VIEW IF EXISTS "+id1;
            sql1="CREATE VIEW "+id1+" AS SELECT order_1.productid ,sum(order_1.cost),product.name,sum(order_1.num) FROM order_1,product" +
                    " where order_1.sellerid=? AND order_1.productid=product.id and order_1.date>? group by order_1.productid";
        }
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        Connection conn0=null;
        PreparedStatement pstmt0=null;
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,mon);
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                if(flag.equals("0")){
                    Reports reports=new Reports();
                    reports.setShopname(rs.getString(3));
                    reports.setCost(rs.getString(2));
                    all.add(reports);
                }else{
                    Reports reports=new Reports();
                    reports.setProductname(rs.getString(3));
                    reports.setNum(rs.getString(4));
                    reports.setProfit(rs.getString(2));
                    all.add(reports);
                }
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
        try
        {
            conn0= ConnectionManager.getConnection();
            pstmt0=conn0.prepareStatement(sql0);
//            pstmt1.setString(1,id);
//            pstmt1.setString(2,mon);
            pstmt0.executeUpdate() ;
        }
        catch (Exception e)
        {
            System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt0);
            ConnectionManager.closeConnection(conn0);
        }
        try
        {
            conn1= ConnectionManager.getConnection();
            pstmt1=conn1.prepareStatement(sql1);
            pstmt1.setString(1,id);
            pstmt1.setString(2,mon);
            pstmt1.executeUpdate() ;
        }
        catch (Exception e)
        {
            System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt1);
            ConnectionManager.closeConnection(conn1);
        }
        return all ;
    }
}
