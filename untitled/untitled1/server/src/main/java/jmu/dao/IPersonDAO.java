package jmu.dao;

import jmu.model.Person;

import java.util.List;

public interface IPersonDAO {
    // 做登陆验证
    public boolean login(Person person) throws Exception ;
    public boolean register(Person person) throws Exception ;

    public void  update(Person person)throws Exception;


    public List<Person> queryAll() throws Exception ;

    public List<Person> queryAll1(String shopid) throws Exception;
}
