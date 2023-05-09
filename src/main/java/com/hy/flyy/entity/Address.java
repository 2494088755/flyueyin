package com.hy.flyy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄勇
 * @since 2023/5/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String nickname;

    private String phone;

    private String city;

    private String address;
}
