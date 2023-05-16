package jmu.utils;

import jmu.db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductUtil {
    public static List<Integer> divideProductPackage( Integer totalPeopleNum) throws SQLException {
        List<Integer> amountList = new ArrayList<Integer>();

        if (totalPeopleNum>0){
            SQLUtils sqlUtils=new SQLUtils();
            int a= sqlUtils.getID("select id from product_detail order by id desc limit 1");//取id
            for (int i=0;i<totalPeopleNum;i++)amountList.add(a++);//分配id
        }

        return amountList;
    }

    public static List<Integer> getList(String pack) throws Exception {
        List<Integer> amountList = new ArrayList<Integer>();
        String sql = "SELECT id FROM product_detail WHERE product_packed=? and product_active=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,pack) ;
            pstmt.setInt(2,0);
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                amountList.add(rs.getInt(1));
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
        return amountList;
    }

    public int getStock(String pack) throws Exception {
        int a=0;
        String sql = "SELECT stock FROM product WHERE produc_packed=?";
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,pack) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                a=rs.getInt(1);
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
        return a;
    }

    public synchronized int check(String toString) throws Exception {
        synchronized (this) {
            int a = 0;
            int id = Integer.parseInt(toString);
            String sql = "SELECT product_active FROM product_detail WHERE id=?";
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                conn = ConnectionManager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                ;
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    a = rs.getInt(1);
                }
            } catch (Exception e) {
                System.out.println(e);
                throw new Exception("操作中出现错误！！！");
            } finally {
                ConnectionManager.closeResultSet(rs);
                ConnectionManager.closeStatement(pstmt);
                ConnectionManager.closeConnection(conn);
            }
            return a;
        }
    }
}
