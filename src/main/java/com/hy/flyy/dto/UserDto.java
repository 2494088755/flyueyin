package com.hy.flyy.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.flyy.entity.User;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 黄勇
 * @since 2023/4/25
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends User implements Serializable {
    private static final long serialVersionUID = -3966424112023283866L;

    //每页查询数量
    private Integer pageSize;

    //当前业
    private Integer curPage;

    //总记录数
    private Long total;

    //分页查询记录封装
    private List<User> userList;

    //范围查询截止日期
    private LocalDateTime untilTime;

    //范围查询开始日期
    private LocalDateTime startTime;

    //确认密码
    private String secondPassword;

    //验证码
    private String code;
}
