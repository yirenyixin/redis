package com.xm.mapper;

import jmu.model.OrderDetails;
import java.util.List;

public interface OrderDetailsMapper {
    int insert(OrderDetails record);

    List<OrderDetails> selectAll();
}