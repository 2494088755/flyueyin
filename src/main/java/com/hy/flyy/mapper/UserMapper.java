package com.hy.flyy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.flyy.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * (User)表数据库访问层
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

