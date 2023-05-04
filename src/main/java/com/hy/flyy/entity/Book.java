package com.hy.flyy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Book)表实体类
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    private static final long serialVersionUID = -7899241014232535512L;
    private Integer id;

    private String name;

    private String author;

    private String image;

    private String description;

    private Double price;

    private Integer stock;

    private Integer categoryId;

    }

