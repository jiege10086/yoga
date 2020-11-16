package com.woniu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.woniu.mapper")
@EnableSwagger2
@EnableFeignClients("com.woniu.fen")
public class CoachStart
{
    public static void main( String[] args )
    {
        SpringApplication.run(CoachStart.class, args);
    }
}
