package com.xm.controller;

import com.xm.Redisson.Employer;

public interface IProductPacketController {

    void get(Employer employer);

    void afterPropertiesSet(String pack) throws Exception;
}
