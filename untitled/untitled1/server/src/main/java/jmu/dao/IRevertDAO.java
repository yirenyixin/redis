package jmu.dao;

import jmu.model.Revert;

import java.util.List;

public interface IRevertDAO {
    // 增加操作
    public void insert(Revert revert) throws Exception ;
    // 删除操作
    public void delete(int RevertID) throws Exception ;
    // 查询全部
    public List<Revert> queryAll(String noteID) throws Exception ;

    public List<Revert> queryAll1() throws Exception ;

    public void deleteall(String noteID) throws Exception ;

    public void update(String img,String name)throws Exception;
}
