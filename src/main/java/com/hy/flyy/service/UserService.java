package com.hy.flyy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.flyy.dto.UserDto;
import com.hy.flyy.entity.User;
import com.hy.flyy.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
public interface UserService extends IService<User> {

//    R login(User user);

    R login(HttpServletRequest request, User user);

    R add(User user);

    R delete(List<User> id);

    R findOne(String username);

    R update(UserDto userDto);

    R query(UserDto userDto);

    R register(UserDto userDto);

    R logout(String username);

    void generateCaptcha(HttpServletResponse response) throws IOException;

    R info(HttpServletRequest request);
}

