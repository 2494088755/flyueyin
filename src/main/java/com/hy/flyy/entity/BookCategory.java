package com.hy.flyy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (BookCategory)表实体类
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCategory implements Serializable {

    private static final long serialVersionUID = 662139020279471151L;
    private Integer id;

    private String name;

    private Integer parentId;

    private String description;


    }

