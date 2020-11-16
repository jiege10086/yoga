package com.woniu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.woniu.mapper")
@EnableSwagger2
@ImportResource("classpath:transaction.xml")
public class Orderstart
{
    public static void main( String[] args )
    {
        SpringApplication.run(Orderstart.class, args);
    }
}
