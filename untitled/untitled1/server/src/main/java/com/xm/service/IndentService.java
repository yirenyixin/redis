package com.xm.service;

import com.xm.mapper.IndentMapper;
import jmu.model.Address;
import jmu.model.Indent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class IndentService implements IIndentService {
    @Autowired
    private IndentMapper indentMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void insert(Integer userId, String productid,String pack) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Indent indent=new Indent();
        Address address = (Address) valueOperations.get(userId);
        String cost= (String) valueOperations.get("cost");
        String shopid=(String) valueOperations.get("shopid");
        indent.setBuyerid(String.valueOf(userId));
        indent.setProductid(Integer.valueOf(productid));
        indent.setAddr(address.getAddr());
        indent.setAddressee(address.getAddressee());
        indent.setCity(address.getCity());
        indent.setNum(1);
        indent.setCost(BigDecimal.valueOf(Long.parseLong(cost)));
        indent.setState("已下单");
        indent.setPhone(address.getPhone());
        indent.setShopid(shopid);
        indent.setBack(String.valueOf(1));
        indent.setAffirm("0");
        indent.setPack(pack);
        indentMapper.insert(indent);
    }
}
