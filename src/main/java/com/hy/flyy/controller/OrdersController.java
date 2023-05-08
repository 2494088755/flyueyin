package com.hy.flyy.controller;

import com.hy.flyy.entity.Orders;
import com.hy.flyy.service.OrdersService;
import com.hy.flyy.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 黄勇
 * @since 2023/5/8
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
@Api("订单控制器")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    // 获取所有订单
    @GetMapping
    public R getAllOrders() {
        return ordersService.findAll();
    }

    // 创建订单
    @PostMapping
    public R createOrder(@RequestBody Orders orders) {
        return R.success(ordersService.save(orders) ? "添加成功" : "添加失败");
    }

    // 获取指定订单
    @GetMapping("/{id}")
    public R getOrderById(@PathVariable Integer id) {
        return ordersService.findById(id);
    }

    // 更新订单
    @PutMapping
    public R updateOrder(@RequestBody Orders orders) {
       return ordersService.updateOrder(orders);
    }

    // 删除订单
    @DeleteMapping("/{id}")
    public R deleteOrder(@PathVariable Integer id) {
        return ordersService.deleteOrder(id);
    }

}
