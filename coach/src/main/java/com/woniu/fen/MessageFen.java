package com.woniu.fen;

import com.woniu.param.CoaMessageParam;
import com.woniu.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient("/")
public interface MessageFen {
    //教练查询给自己的留言
    @RequestMapping("/selectCoaMessage")
    public JSONResult selectCoaMessage(@RequestHeader("X-token") String token, int pageIndex, int pageSize, String status);

    //教练新增留言
    @RequestMapping("/coainsertMessage")
    public JSONResult insertMessage(@RequestHeader("X-token") String token, CoaMessageParam coaMessageParam);
}
