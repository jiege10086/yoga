package com.woniu.fen;

import com.woniu.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient("Order")
public interface OrderFen {
    @RequestMapping("/coaorder")
    public JSONResult selectOrderByCoa(@RequestHeader("X-token") String token, int pageSize, int pageIndex, int status);


}
