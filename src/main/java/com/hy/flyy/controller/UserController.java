package com.hy.flyy.controller;

import com.hy.flyy.dto.UserDto;
import com.hy.flyy.entity.User;
import com.hy.flyy.service.UserService;
import com.hy.flyy.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 黄勇
 * @since 2023/4/25
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(value = "User控制层", tags = "用户相关接口")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    @ApiImplicitParam(name = "user", value = "用户信息")
    public R login(HttpServletRequest request,@RequestBody User user) {
        return userService.login(request,user);
    }

    @PostMapping
    @ApiOperation("添加用户")
    public R addUser(@RequestBody @ApiParam("用户信息") User user) {
        return userService.add(user);
    }

    @DeleteMapping
    @ApiOperation("删除用户")
    public R deleteUser(@RequestBody @ApiParam("删除用户的id") List<User> ids) {
        return userService.delete(ids);
    }

    @GetMapping("/{username}")
    @ApiOperation("根据用户名查询用户")
    public R findUser(@PathVariable @ApiParam("要查询用户的id") String username) {
        return userService.findOne(username);
    }

    @PutMapping
    @ApiOperation("修改用户信息")
    public R update(@RequestBody @ApiParam("修改的用户") UserDto userDto) {
        return userService.update(userDto);
    }

    @PostMapping("/page")
    @ApiOperation("条件分页查询")
    public R conditionalPaginationQuery(@RequestBody UserDto userDto) {
        return userService.query(userDto);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public R register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    @GetMapping("/logout/{username}")
    @ApiOperation("退出")
    public R logout(@PathVariable String username) {
        return userService.logout(username);
    }

    @GetMapping("/captcha")
    @ApiOperation("生成验证码")
    public void captcha(HttpServletResponse response) throws IOException {
        userService.generateCaptcha(response);
    }

    @GetMapping("/info")
    @ApiOperation("查询当前用户信息")
    public R userInfo(HttpServletRequest request) {
        return userService.info(request);
    }
}
