package com.hy.flyy.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (Orders)表实体类
 *
 * @author 黄勇
 * @since 2023-05-08 19:49:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Serializable {
    private static final long serialVersionUID = 2404070972363886321L;
    private Integer id;

    private Integer userId;

    private Double totalPrice;

    private LocalDateTime orderTime;

    private LocalDateTime paymentTime;

    private LocalDateTime shippingTime;

    private LocalDateTime deliveryTime;

    private String addressId;

    private String paymentMethod;

    private String paymentStatus;

    private String shippingStatus;

    private Integer quantity;

}

