package jmu.dao.impl;

import jmu.dao.IWalletDAO;
import jmu.db.ConnectionManager;
import jmu.model.Wallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WalletDAOImpl implements IWalletDAO {
    @Override
    public void insert(String personid) throws Exception {
//        Wallet wallet=new Wallet();
//        wallet.setUserid(personid);
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        sqlSession.insert("jmu.mapper.PersonMapper.insert",wallet);
//        sqlSession.commit();	//提交事务
//        sqlSession.close();
        String sql = "INSERT INTO wallet(personid,balance) VALUES(?,?)" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,personid) ;
            pstmt.setString(2,"0");
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
    public void update(Wallet wallet) throws Exception {
        String sql = "update wallet set balance=? where personid=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, wallet.getBalance()); ;
            pstmt.setString(2, wallet.getUserid());
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
    public List<Wallet> getbalance(String my) throws Exception {
        List<Wallet> all = new ArrayList<Wallet>() ;
        String sql = "SELECT balance FROM wallet where personid=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,my) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                Wallet wallet=new Wallet();
                wallet.setBalance(rs.getString(1));
                all.add(wallet);
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
    public void reduce(Wallet wallet_buyer) throws Exception {
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        sqlSession.update("jmu.mapper.WalletMapper.reduce",wallet_buyer);
//        sqlSession.commit();	//提交事务
//        sqlSession.close();

        String sql = "update wallet set balance=balance-? where personid=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, wallet_buyer.getBalance()); ;
            pstmt.setString(2, wallet_buyer.getUserid());
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
    public void add(Wallet wallet_seller) throws Exception {
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        sqlSession.update("jmu.mapper.WalletMapper.add",wallet_seller);
//        sqlSession.commit();	//提交事务
//        sqlSession.close();

        String sql = "update wallet set balance=balance+? where personid=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, wallet_seller.getBalance()); ;
            pstmt.setString(2, wallet_seller.getUserid());
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
}
