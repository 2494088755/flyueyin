package com.hy.flyy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * (User)表实体类
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户模型")
public class User implements Serializable {
    private static final long serialVersionUID = -1245840966479021865L;

    @ApiModelProperty("唯一标识")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;

    @ApiModelProperty("创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty("修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty("逻辑删除")
    @TableField
    @TableLogic
    private Integer isDeleted;
    //0普通用户1管理员
    @ApiModelProperty("身份")
    private Integer type;

    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("电话号码")
    private String phone;

}

