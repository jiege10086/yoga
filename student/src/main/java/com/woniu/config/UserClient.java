package com.woniu.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient("product") //另一个服务名
public interface UserClient {
    @GetMapping("weight")//另一个服务里面的请求地址
     String test();

}
