package com.hy.flyy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hy.flyy.entity.User;
import com.hy.flyy.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;

/**
 * @author 黄勇
 * @since 2023/4/25
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void redisTest() {
        User user = new User();
        user.setUsername("aaa");

//        redisTemplate.opsForValue().set("name", user);
        redisTemplate.delete("name");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

    @Test
    public void test() {
//        System.out.println(redisUtils.getCacheObject("user_admin123").toString());
        User user_admin123 = JSON.parseObject(redisUtils.getCacheObject("user_admin123"), User.class);
        System.out.println(user_admin123);
//        System.out.println(user);
    }

}
