package jmu.dao.impl;

import jmu.dao.IPersonDAO;
import jmu.utils.MyBatisUtils;
import jmu.model.Person;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements IPersonDAO {
    /*
     * 功能： · 判断是否是正确的用户名或密码 · 从数据库中取出用户的真实姓名
     */
    public List<Person> queryAll() throws Exception
    {
        SqlSession sqlSession1 =MyBatisUtils.getSession();
        List<Person> all=sqlSession1.selectList("jmu.mapper.PersonMapper.getall");
        sqlSession1.commit();
        sqlSession1.close();
        return all ;
    }
    public List<Person> queryAll1(String shopid) throws Exception
    {
        SqlSession sqlSession1 =MyBatisUtils.getSession();
        List<Person> all=sqlSession1.selectList("jmu.mapper.PersonMapper.getall");
        List<Person> all1=new ArrayList<Person>();
        for(int i=0;i<all.size();i++){
            String a=all.get(i).getId();
            if(a.equals(shopid)){
                Person person=new Person();
                person= all.get(i);
                all1.add(person);
                break;
            }
        }
        sqlSession1.commit();
        sqlSession1.close();
        return all1 ;
    }
    public boolean login(Person person) throws Exception {
        boolean flag = false;
        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
        Person person1=sqlSession.selectOne("jmu.mapper.PersonMapper.login",person);
        if(person1!=null){
            flag=true;
            person.setName(person1.getName());
            person.setImg(person1.getImg());
            person.setFlag(person1.getFlag());
            person.setId(person1.getId());
        }
        sqlSession.close();

//        Connection conn=null;
//        PreparedStatement pstmt=null;
//        ResultSet rs=null;
//        String sql = "SELECT name,img,id,flag FROM person WHERE id=? and password=?";
//        try {
//            conn = ConnectionManager.getConnection();
//            pstmt=conn.prepareStatement(sql);
//            pstmt.setString(1, person.getId());
//            pstmt.setString(2, person.getPassword());
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                flag = true;
//                person.setName(rs.getString(1));
//                person.setImg(rs.getString(2));
//                person.setId(rs.getString(3));
//                person.setFlag(Integer.parseInt(rs.getString(4)));
//            }
//
//        } catch (Exception e) {
//            throw new Exception("操作出现错误！！！");
//        } finally {
//            ConnectionManager.closeResultSet(rs);
//            ConnectionManager.closeStatement(pstmt);
//            ConnectionManager.closeConnection(conn);
//        }
        return flag;
    }

    @Override
    public boolean register(Person person) throws Exception {
//        boolean flag = false;
        boolean flag = false;
        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
        sqlSession.insert("jmu.mapper.PersonMapper.insert",person);
        sqlSession.commit();	//提交事务
        sqlSession.close();
//        if(person1!=null){
//            flag=true;
//            person.setName(person1.getName());
//            person.setImg(person1.getImg());
//        }
//        Connection conn=null;
//        PreparedStatement pstmt=null;
//        ResultSet rs=null;
//        String sql = "insert into person (id,name,password) value (?,?,?)";
//        try {
//            conn = ConnectionManager.getConnection();
//            pstmt=conn.prepareStatement(sql);
//            pstmt.setString(1, person.getId());
//            pstmt.setString(2, person.getName());
//            pstmt.setString(3, person.getPassword());
//            pstmt.executeUpdate() ;
////            rs = pstmt.executeQuery();
////            if (rs.next()) {
////                flag = true;
////                person.setName(rs.getString(1));
////            }
//        } catch (Exception e) {
//            throw new Exception("操作出现错误！！！");
//        } finally {
////            ConnectionManager.closeResultSet(rs);
//            ConnectionManager.closeStatement(pstmt);
//            ConnectionManager.closeConnection(conn);
//        }
        return true;
    }

    @Override
    // 修改操作
    public void update(Person person) throws Exception
    {
//        boolean flag = false;
        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
        Person person1=sqlSession.selectOne("jmu.mapper.PersonMapper.update",person);
        if(person1!=null){
//            flag=true;
            person.setImg(person1.getImg());
        }
        sqlSession.close();
//        String sql = "UPDATE person SET img=? WHERE name=? ";
//        Connection conn=null;
//        PreparedStatement pstmt=null;
//        try
//        {
//            conn=ConnectionManager.getConnection();
//            pstmt=conn.prepareStatement(sql);
//            pstmt.setString(1,person.getImg()) ;
//            pstmt.setString(2,person.getName()) ;
//            pstmt.executeUpdate() ;
//            person.setImg(person.getImg());
//        }
//        catch (Exception e)
//        {
//            throw new Exception("操作中出现错误！！！") ;
//        }
//        finally
//        {
//            ConnectionManager.closeStatement(pstmt);
//            ConnectionManager.closeConnection(conn);
//        }
    }


}
