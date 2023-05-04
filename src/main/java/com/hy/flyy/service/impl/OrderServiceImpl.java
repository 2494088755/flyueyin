package com.hy.flyy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.flyy.mapper.OrderMapper;
import com.hy.flyy.entity.Order;
import com.hy.flyy.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * (Order)表服务实现类
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}

