package com.woniu.fen;

import com.woniu.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("order")
public interface OrderFen {
    //教练查询自己的订单
    @RequestMapping("/tOrder/coaorder")
    public JSONResult selectOrderByCoa(@RequestParam int pageSize,@RequestParam int pageIndex,@RequestParam int status);

    //修改订单状态
    @RequestMapping("/tOrder/updateOrd")
    public JSONResult updateOrderByCoa(@RequestParam int ordId,@RequestParam int status);

}
