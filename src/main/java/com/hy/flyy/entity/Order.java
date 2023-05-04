package com.hy.flyy.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Order)表实体类
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 7287851633009576456L;
    private Integer id;

    private Long orderNo;

    private Integer userId;

    private Double amount;
    //0未发货1已发货2已完成
    private Integer status;

    private Date createTime;


}

