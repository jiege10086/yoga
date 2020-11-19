package com.woniu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients("com.woniu.config")
@MapperScan("com.woniu")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
