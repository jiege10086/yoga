package com.woniu.config;

import com.woniu.commons.JsonResult;
import com.woniu.dto.TUser;
import com.woniu.exception.NumberNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
@FeignClient("student") //另一个服务名
public interface TMessageClient {
        @GetMapping("/searchUserById")
        public TUser searchUserById(int uId) throws NumberNotFoundException;

}
