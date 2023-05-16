package jmu.dao;

import jmu.model.Address;


import java.util.List;

public interface IaddressDAO {
    public List<Address> queryAll(Address addr) throws Exception ;

    public void update(Address addr) throws Exception;

    public void insert(Address addr)throws Exception;

    public void delect(Address addr)throws Exception;
}
