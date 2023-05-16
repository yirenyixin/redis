package jmu.dao.impl;

import jmu.dao.IaddressDAO;
import jmu.db.ConnectionManager;

import jmu.model.Address;
import jmu.utils.SQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class addressDAOImpl implements IaddressDAO {
    @Override
    public void delect(Address addr) throws Exception {
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        sqlSession.delete("jmu.mapper.addressMapper.delete",addr);
//        sqlSession.commit();	//提交事务
//        sqlSession.close();

        String sql = "DELETE FROM address WHERE id=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;

            try {
                conn = ConnectionManager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, addr.getId());
                pstmt.executeUpdate();
            } catch (Exception e) {
                throw new Exception("操作中出现错误！！！");
            } finally {
                ConnectionManager.closeStatement(pstmt);
                ConnectionManager.closeConnection(conn);
            }
    }

    public List<Address> queryAll(Address addr) throws Exception
    {
        String uid=addr.getUserid();
        List<Address> all = new ArrayList<Address>() ;
        String sql = "SELECT id,city,addr,flag,phone,userid,addressee FROM address where userid=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,uid) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                Address add=new Address();
                add.setId(rs.getInt(1));
                add.setCity(rs.getString(2));
                add.setAddr(rs.getString(3));
                add.setFlag(rs.getString(4));
                add.setPhone(rs.getString(5));
                add.setUserid(rs.getString(6));
                add.setAddressee(rs.getString(7));
                all.add(add) ;
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
//        SqlSession sqlSession1 =MyBatisUtils.getSession();
//        List<address> all=sqlSession1.selectList("jmu.mapper.addressMapper.getall");
//        sqlSession1.commit();
//        sqlSession1.close();
        return all ;
    }

    @Override
    public void update(Address addr) throws Exception {
        addr.setFlag("0");
        String sql = "UPDATE address SET flag=? WHERE userid=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,addr.getFlag()) ;
            pstmt.setString(2,addr.getUserid()) ;
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

        addr.setFlag("1");
        String sql1 = "UPDATE address SET flag=? WHERE userid=? and id=?" ;
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        try
        {
            conn1= ConnectionManager.getConnection();
            pstmt1=conn1.prepareStatement(sql1);
            pstmt1.setString(1,addr.getFlag()) ;
            pstmt1.setString(2,addr.getUserid()) ;
            pstmt1.setInt(3,addr.getId());
            pstmt1.executeUpdate() ;
        }
        catch (Exception e)
        {
            // System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt1);
            ConnectionManager.closeConnection(conn1);
        }
    }

    @Override
    public void insert(Address addr) throws Exception {
//        SqlSession sqlSession1 =MyBatisUtils.getSession();
//        String id=sqlSession1.selectOne("jmu.mapper.addressMapper.getID");
//        sqlSession1.commit();
//        sqlSession1.close();
//        addr.setId(id);
//        String sql1 = "select id from address order by id desc limit 1" ;
////        String sql1 ="SELECT revertID FROM revert where 1 order by revertID asc limit 1,1";
//
//
//
//        Connection conn1=null;
//        PreparedStatement pstmt1=null;
//        conn1= ConnectionManager.getConnection();
//        pstmt1=conn1.prepareStatement(sql1);
//        ResultSet rs = pstmt1.executeQuery(sql1);
//        String id = null;
//        while (rs.next()) {
//            id=rs.getString(1);
//        }
        SQLUtils sqlUtils=new SQLUtils();

        int a= sqlUtils.getID("select id from address order by id desc limit 1")+1;

        addr.setId(a);
        String sql = "INSERT INTO address(id,city,addr,flag,phone,userid,addressee) VALUES(?,?,?,?,?,?,?)" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,addr.getId());
            pstmt.setString(2,addr.getCity());
            pstmt.setString(3,addr.getAddr());
            pstmt.setString(4,addr.getFlag());
            pstmt.setString(5,addr.getPhone());
            pstmt.setString(6,addr.getUserid());
            pstmt.setString(7,addr.getAddressee());
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
        String flag=addr.getFlag();
        if (flag.equals("1")){
            update(addr);
        }

    }

}
