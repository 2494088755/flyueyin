package com.hy.flyy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.flyy.mapper.OrderBookMapper;
import com.hy.flyy.entity.OrderBook;
import com.hy.flyy.service.OrderBookService;
import org.springframework.stereotype.Service;

/**
 * (OrderBook)表服务实现类
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
@Service("orderBookService")
public class OrderBookServiceImpl extends ServiceImpl<OrderBookMapper, OrderBook> implements OrderBookService {

}

