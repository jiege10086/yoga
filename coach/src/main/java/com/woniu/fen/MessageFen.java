package com.woniu.fen;

import com.woniu.param.CoaMessageParam;
import com.woniu.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("message")
public interface MessageFen {
    //教练查询给自己的留言
    @RequestMapping("/message/selectCoaMessage")
    public JSONResult selectCoaMessage( @RequestParam int pageIndex,@RequestParam int pageSize,@RequestParam String status);

    //教练新增留言
    @RequestMapping("/message/coainsertMessage")
    public JSONResult insertMessage(@RequestParam String coaMessageParam);
}
