package jmu.dao.impl;

import jmu.db.ConnectionManager;
import jmu.utils.MyBatisUtils;
import jmu.model.Revert;
import jmu.dao.IRevertDAO;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RevertDAOImpl implements IRevertDAO {
    public List<Revert> queryAll1() throws Exception
    {
        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
        List<Revert> all1=sqlSession.selectList("jmu.mapper.RevertMapper.queryAll1");
        sqlSession.close();
//        List<Revert> all1 = new ArrayList<Revert>() ;
//        String sql = "SELECT revertID,noteID,content,writer,author FROM revert";
//        Connection conn=null;
//        PreparedStatement pstmt=null;
//        ResultSet rs=null;
//        try
//        {
//            conn= ConnectionManager.getConnection();
//            pstmt=conn.prepareStatement(sql);
//            rs = pstmt.executeQuery() ;
//            while(rs.next())
//            {
//                Revert revert = new Revert() ;
//                revert.setRevertID(rs.getInt(1));
//                revert.setNoteID(rs.getString(2));
//                revert.setContent(rs.getString(3));
//                revert.setWriter(rs.getString(4));
//                revert.setAuthor(rs.getString(5));
//                 all1.add(revert) ;
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e) ;
//            throw new Exception("操作中出现错误！！！") ;
//        }
//        finally
//        {
//            ConnectionManager.closeResultSet(rs);
//            ConnectionManager.closeStatement(pstmt);
//            ConnectionManager.closeConnection(conn);
//        }
        return all1 ;
    }
    // 查询全部
    public List<Revert> queryAll(String noteID) throws Exception
    {
//        Revert revert=new Revert();
//        revert.setNoteID(noteID);
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        List<Revert> all1 = new ArrayList<Revert>() ;
//         all1=sqlSession.selectList("jmu.mapper.RevertMapper.queryAll",revert);
//        sqlSession.close();


        List<Revert> all1 = new ArrayList<Revert>() ;
        String sql = "SELECT revertID,noteID,content,writer,author,img,writeDate,authorimg FROM revert where noteID=?";
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,noteID);
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                Revert revert = new Revert() ;
                revert.setRevertID(rs.getInt(1));
                revert.setNoteID(rs.getString(2));
                revert.setContent(rs.getString(3));
                revert.setWriter(rs.getString(4));
                revert.setAuthor(rs.getString(5));
                revert.setImg(rs.getString(6));
                revert.setWriteDate(rs.getString(7));
                revert.setAuthorimg(rs.getString(8));
                all1.add(revert) ;
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
        return all1 ;
    }


    // 新增回复
    public void insert(Revert revert) throws Exception
    {
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        List<Revert> all1=sqlSession.selectList("jmu.mapper.RevertMapper.queryAll1",noteID);
        String sql1 = "select revertID from revert order by revertID desc limit 1" ;
//        String sql1 ="SELECT revertID FROM revert where 1 order by revertID asc limit 1,1";


        String sql = "INSERT INTO revert(revertID,noteID,content,writer,author,img,writeDate) VALUES(?,?,?,?,?,?,?)" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        Connection conn1=null;
        PreparedStatement pstmt1=null;
        int index=1;
        try
        {
             conn1= ConnectionManager.getConnection();
             pstmt1=conn1.prepareStatement(sql1);
            ResultSet rs = pstmt1.executeQuery(sql1);
            while (rs.next()) {
                int  revertID=rs.getInt(1);
                revertID+=1;
                revert.setRevertID(revertID);
            }
            ConnectionManager.closeStatement(pstmt1);
            ConnectionManager.closeConnection(conn1);
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,revert.getRevertID()); ;
            pstmt.setString(2,revert.getNoteID()); ;
            pstmt.setString(3,revert.getContent()) ;
            pstmt.setString(4,revert.getWriter()) ;
            pstmt.setString(5,revert.getAuthor()) ;
            pstmt.setString(6,revert.getImg());
            pstmt.setString(7,revert.getWriteDate());
            while(revert.getWriter()!=null) {
                pstmt.executeUpdate();
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
    // 删除操作
    public void delete(int RevertID) throws Exception
    {
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        sqlSession.delete("jmu.mapper.RevertMapper.delete",RevertID);
//        sqlSession.commit();	//提交事务
//        sqlSession.close();
//
        String sql = "DELETE FROM revert WHERE revertID=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,RevertID); ;
            pstmt.executeUpdate() ;
        }
        catch (Exception e)
        {
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
    }

    public void deleteall(String noteID) throws Exception
    {
//        Revert revert=new Revert();
//        revert.setNoteID(noteID);
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        sqlSession.delete("jmu.mapper.RevertMapper.deleteAll",noteID);
//        sqlSession.commit();	//提交事务
//        sqlSession.close();


        String sql = "DELETE FROM revert WHERE noteID=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,noteID); ;
            pstmt.executeUpdate() ;
        }
        catch (Exception e)
        {
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void update(String img,String name) throws Exception {
//        Revert revert=new Revert();
//        revert.setAuthorimg(img);
//        revert.setAuthor(name);
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        sqlSession.insert("jmu.mapper.RevertMapper.update",revert);
//        sqlSession.close();


        String sql = "UPDATE revert SET authorimg=? WHERE writer=? ";
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,img) ;
            pstmt.setString(2,name) ;
            pstmt.executeUpdate() ;
        }
        catch (Exception e)
        {
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
    }
}
