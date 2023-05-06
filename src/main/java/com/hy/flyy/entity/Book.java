package com.hy.flyy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
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
@ApiModel("书本模型")
public class Book implements Serializable {

    private static final long serialVersionUID = -7899241014232535512L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    //书名
    private String name;

    //作者
    private String author;

    //图片
    private String image;

    //描述
    private String description;

    //价格
    private Double price;

    //库存
    private Integer stock;

    //类型
    private Integer categoryId;

    @TableLogic
    private Integer isDeleted;
}

