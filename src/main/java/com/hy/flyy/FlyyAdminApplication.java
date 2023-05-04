package com.hy.flyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//开启定时任务
//@EnableScheduling
@MapperScan("com.hy.flyy.mapper")
@EnableWebMvc
@EnableTransactionManagement
public class FlyyAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlyyAdminApplication.class, args);
    }

}
