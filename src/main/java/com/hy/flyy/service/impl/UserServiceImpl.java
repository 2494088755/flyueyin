package com.hy.flyy.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.flyy.dto.UserDto;
import com.hy.flyy.entity.User;
import com.hy.flyy.mapper.UserMapper;
import com.hy.flyy.service.UserService;
import com.hy.flyy.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * (User)表服务实现类
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
@Service("userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 用户登录业务逻辑
     *
     * @param request
     * @param user
     * @return
     */
    @Override
    public R login(HttpServletRequest request, User user) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User queryUser = getOne(queryWrapper);

        if (Objects.isNull(queryUser)) {
            return R.fail("用户名或密码错误");
        }

        String enSalt = PwdUtils.encryption(user.getPassword(), queryUser.getSalt());

        if (!Objects.equals(enSalt, queryUser.getPassword())) {
            return R.fail("用户名或密码错误");
        }

        String token = JwtUtils.generateToken(user.getUsername());

        redisUtils.setCacheObject("user_" + user.getUsername(), queryUser);
//        System.out.println(queryUser);
        redisUtils.expire("user_" + user.getUsername(), 24, TimeUnit.HOURS);

        log.info("用户{}登陆成功", user.getUsername());

        return R.success(token);
    }

    /**
     * 添加用户业务逻辑
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public R add(User user) {
        System.out.println("2" + user);

        if (ParameterVerificationUtils.checkAll(user.getUsername(), user.getPassword(), user.getEmail())) {
            log.info("基础信息有误");
            return R.fail("参数有误");
        }

        if (checkPhone(user.getPhone())) {
            log.info("手机号有误");
            return R.fail("参数有误");
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername()).or().eq(User::getEmail, user.getEmail());
        User queryUser = getOne(queryWrapper);

        if (!Objects.isNull(queryUser)) {
            return R.fail("用户已存在");

        }

        String salt = PwdUtils.generateSalt();
        user.setSalt(salt);
        String encryption = PwdUtils.encryption(user.getPassword(), salt);
        user.setPassword(encryption);

        if (StrUtil.isBlank(user.getPhone())) {
            user.setPhone("");
        }

        save(user);

        log.info("用户{}注册成功", user.getUsername());
        return R.success("添加成功");
    }

    /**
     * 根据id删除用户
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public R delete(List<User> ids) {

        System.out.println(ids);

        log.info("用户id：{}删除成功", ids);

        return removeByIds(ids) ? R.success() : R.fail("删除失败");
    }


    /**
     * 根据用户名查询用户
     */
    @Override
    public R findOne(String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);
        User queryUser = getOne(lambdaQueryWrapper);

        if (queryUser != null) {
            log.info("查询到用户{}", queryUser.getUsername());
            queryUser.setSalt(null);
            queryUser.setPassword(null);
            return R.success(queryUser);
        }

        return R.fail("没有该用户");
    }

    /**
     * 修改用户信息
     */
    @Override
    public R update(UserDto userDto) {

        if (!StrUtil.isBlank(userDto.getPassword())) {
            if (ParameterVerificationUtils.checkAll(userDto.getUsername(), userDto.getPassword(), userDto.getEmail())) {
                log.info("修改失败，用户名，密码，邮箱有误");
                return R.fail("参数有误");
            }
        } else {
            if (!ParameterVerificationUtils.checkUsername(userDto.getUsername()) && !ParameterVerificationUtils.checkEmail(userDto.getEmail())) {
                log.info("修改失败，用户名或邮箱有误");
                return R.fail("参数有误");
            }
        }

        if (StrUtil.isBlank(userDto.getSecondPassword())) {
            userDto.setPassword(null);
        } else {
            if (!Objects.equals(userDto.getSecondPassword(), userDto.getPassword())) {
                log.info("修改失败，二次密码有误");
                return R.fail("参数有误");
            }
            String salt = PwdUtils.generateSalt();
            userDto.setSalt(salt);
            String encryption = PwdUtils.encryption(userDto.getPassword(), salt);
            userDto.setPassword(encryption);
        }

        if (checkPhone(userDto.getPhone())) {
            log.info("修改失败，手机号有误");
            return R.fail("参数有误");
        }

        return updateById(userDto) ? R.success("修改成功") : R.fail("修改失败");
    }

    /**
     * 条件分页查询
     *
     * @param userDto
     * @return
     */
    @Override
    public R query(UserDto userDto) {
        Page<User> page = new Page<>(userDto.getCurPage(), userDto.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        LocalDateTime startTime = userDto.getStartTime();
        LocalDateTime untilTime = userDto.getUntilTime();

        if (startTime != null || untilTime != null) {
            queryWrapper.between(User::getCreateAt, startTime, untilTime);
        }

        if (!StrUtil.isBlank(userDto.getUsername())) {
            queryWrapper.like(User::getUsername, userDto.getUsername());
        }
        if (!StrUtil.isBlank(userDto.getEmail())) {
            queryWrapper.like(User::getEmail, userDto.getEmail());
        }

        page(page, queryWrapper);

        List<User> users = page.getRecords();
        long total = page.getTotal();

        UserDto queryList = new UserDto();
        queryList.setUserList(users);
        queryList.setTotal(total);

        return R.success(queryList);
    }

    /**
     * 用户注册
     *
     * @param userDto
     * @return
     */
    @Override
    public R register(UserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getSecondPassword())) {
            return R.fail("两次密码不匹配");
        }

        if (!Objects.equals(userDto.getCode(), redisUtils.getCacheObject("code"))) {
            return R.fail("验证码错误");
        }

        System.out.println("1" + userDto);

        redisUtils.deleteObject("code");

        return add(userDto);
    }

    /**
     * 登出，清除缓存
     *
     * @param username
     * @return
     */
    @Override
    public R logout(String username) {
        redisUtils.deleteObject("user_" + username);

        log.info("用户{}已退出", username);

        return R.success("退出成功");
    }

    /**
     * 生成验证码及图片
     *
     * @param response
     * @throws IOException
     */
    @Override
    public void generateCaptcha(HttpServletResponse response) throws IOException {
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(100, 40, 4, 4);
        response.setContentType("image/png");
        response.setHeader("Pragma", "No-cache");
        shearCaptcha.write(response.getOutputStream());

        redisUtils.setCacheObject("code", shearCaptcha.getCode(), 5, TimeUnit.MINUTES);
        log.info("验证码:{}", shearCaptcha.getCode());

//        redisUtils.setCacheObject("");
        response.getOutputStream().close();
    }

    /**
     * 查询当前用户信息
     *
     * @param request
     * @return
     */
    @Override
    public R info(HttpServletRequest request) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        String username = JwtUtils.getClaimsByToken(request.getHeader("Authorization")).getSubject();
        queryWrapper.eq(User::getUsername, username);

        User user = this.getOne(queryWrapper);

        HashMap<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("type", user.getType().toString());
        return R.success(map);
    }

    /**
     * 校验基本参数
     *
     * @param
     */
    public boolean checkPhone(String phone) {
        //有值
        if (!StrUtil.isBlank(phone)) {
            //并且为可用值返回true
            return !ParameterVerificationUtils.checkPhone(phone);
        }
        return false;
    }
}

