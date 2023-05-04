package com.hy.flyy;

import com.hy.flyy.dto.UserDto;
import com.hy.flyy.entity.User;
import com.hy.flyy.service.UserService;
import com.hy.flyy.utils.R;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void loginTest() {

    }

    @Test
    public void saveTest() {
        for (int i = 0; i < 1; i++) {
            User user = new User();
            user.setId( i);
            user.setUsername("admin"+i);
            user.setPassword("123456");
            user.setType(1);
            R add = userService.add(user);
        }
    }

    @Test
    public void updateTest() {
    }

    @Test
    public void findOneTest() {
//        R one = userService.findOne(1);
    }

    @Test
    public void deleteTest() {
        User user = new User();
        User user2 = new User();
        user.setId(1);
        user2.setId(2);
        R delete = userService.delete(Arrays.asList(user,user2));
        System.out.println(delete);
    }

    @Test
    public void pageQueryTest() {
        int curPage = 2;
        int pageSize = 5;
        LocalDateTime startTime = LocalDateTime.of(2023, 4, 25, 20, 0);
        LocalDateTime untilTime = LocalDateTime.of(2023, 4, 25, 23, 0);

        UserDto userDto = new UserDto();
        userDto.setCurPage(curPage);
        userDto.setPageSize(pageSize);
        userDto.setStartTime(startTime);
        userDto.setUntilTime(untilTime);

        R query = userService.query(userDto);
        System.out.println(query);
    }

    @Test
    public void test() {

    }
}
