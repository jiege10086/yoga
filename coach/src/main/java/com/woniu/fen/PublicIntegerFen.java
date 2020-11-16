package com.woniu.fen;

import com.woniu.param.DynamicParam;
import com.woniu.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


//@FeignClient("publicinterface")
public interface PublicIntegerFen {

    @RequestMapping("/friend")
    public boolean friend(int selectId,int accpetId);

}
