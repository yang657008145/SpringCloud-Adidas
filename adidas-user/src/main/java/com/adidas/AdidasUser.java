package com.adidas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.adidas.dao.UserMapper")
public class AdidasUser {
    public static void main(String[] args) {
        SpringApplication.run(AdidasUser.class, args);
    }
}
