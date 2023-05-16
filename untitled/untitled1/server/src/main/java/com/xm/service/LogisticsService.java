package com.xm.service;


import com.xm.mapper.LogisticsMapper;
import jmu.model.Logistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jmu.utils.SQLUtils;

import java.sql.SQLException;


@Service
public class LogisticsService implements ILogisticsService {

    private static final Logger log= LoggerFactory.getLogger(LogisticsService.class);
    @Autowired
    private LogisticsMapper logisticsMapper;

    @Override
    public synchronized int insert() {//插入加锁否则设置jmeter并发时间

        SQLUtils sqlUtils=new SQLUtils();
        int id= 0;
        synchronized (this) {//加锁防止写入冲突
            try {
                id = sqlUtils.getID("select orderid from logistics order by orderid desc limit 1");
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            id = logisticsMapper.getID();
            Logistics logistics = new Logistics();
            logistics.setOrderid(id);
            logistics.setState("已下单");
            logistics.setReturn_goods("1");
            logisticsMapper.insert(logistics);
        }
        return id;
    }
}