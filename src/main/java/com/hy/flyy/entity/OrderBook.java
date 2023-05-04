package com.hy.flyy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (OrderBook)表实体类
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBook implements Serializable {

    private static final long serialVersionUID = 3030827460382063499L;
    private Integer id;

    private Integer orderId;

    private Integer bookId;

    private Integer count;


}

