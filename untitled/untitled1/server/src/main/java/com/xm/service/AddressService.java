package com.xm.service;

import com.xm.mapper.AddressMapper;
import jmu.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public Address getAddress(Integer userId) {
        String id= String.valueOf(userId);
        Address address=new Address();
        address=addressMapper.getAddress(id);
        return address;
    }
}
