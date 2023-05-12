package com.hy.flyy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.flyy.entity.Orders;
import com.hy.flyy.utils.R;

/**
 * (Order)表服务接口
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
public interface OrdersService extends IService<Orders> {

    R findAll();

    R findById(Integer id);

    R updateOrder(Orders orders);

    R deleteOrder(Integer id);

    R add(Orders orders);
}

