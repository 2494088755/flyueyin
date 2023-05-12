package com.hy.flyy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.flyy.entity.Orders;
import com.hy.flyy.mapper.OrdersMapper;
import com.hy.flyy.service.OrdersService;
import com.hy.flyy.utils.R;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * (Order)表服务实现类
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
@Service("orderService")
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Override
    public R findAll() {
        return R.success(this.list());
    }

    @Override
    public R findById(Integer id) {
        return null;
    }

    @Override
    public R updateOrder(Orders orders) {
        return null;
    }

    @Override
    public R deleteOrder(Integer id) {
        return null;
    }

    @Override
    public R add(Orders orders) {
        System.out.println(orders);
        orders.setOrderTime(LocalDateTime.now());
        orders.setPaymentStatus("0");
        save(orders);
        return R.success();
    }
}

