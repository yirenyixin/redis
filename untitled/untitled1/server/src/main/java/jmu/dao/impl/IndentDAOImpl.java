package jmu.dao.impl;

import jmu.dao.IIndentDAO;
import jmu.model.Address;
import jmu.model.Indent;
import jmu.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class IndentDAOImpl implements IIndentDAO {
    @Override
    public List<Indent> queryAll0(String id) {
        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
        List<Indent> list=  sqlSession.selectList("jmu.mapper.IndentMapper.queryAll0",id);
        sqlSession.commit();	//提交事务
        sqlSession.close();
        return list;
    }

    @Override
    public void updateAffirm(String id) {
        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
        sqlSession.update("jmu.mapper.IndentMapper.updateAffirm",Integer.parseInt(id));
        sqlSession.commit();	//提交事务
        sqlSession.close();
    }
}
