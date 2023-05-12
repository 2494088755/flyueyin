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

    @GetMapping
    public R getAllOrders() {
        return ordersService.findAll();
    }

    @PostMapping
    public R createOrder(@RequestBody Orders orders) {
        return ordersService.add(orders);
    }

    @GetMapping("/{id}")
    public R getOrderById(@PathVariable Integer id) {
        return ordersService.findById(id);
    }

    @PutMapping
    public R updateOrder(@RequestBody Orders orders) {
       return ordersService.updateOrder(orders);
    }

    @DeleteMapping("/{id}")
    public R deleteOrder(@PathVariable Integer id) {
        return ordersService.deleteOrder(id);
    }

}
