package com.woniu.fen;

import com.woniu.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("signing")
public interface Signingfen {
    //对场馆发起签约请求
    @RequestMapping("/tSigning/insertSigningByCoa")
    public JSONResult insertSigningByCoa( @RequestParam int venId,@RequestParam String detail);

    //查询自己的签约场馆信息
    @RequestMapping("/tSigning/selectSigningByCoa")
    public  JSONResult selectSigningByCoa(@RequestParam int agreeStatus,@RequestParam int status,@RequestParam int pageSize,@RequestParam int pageIndex);

    //教练发起请求同意或者拒绝
    @RequestMapping("/tSigning/updateSigningByCoa")
    public void updateSigningByCoa(@RequestParam int sigId,@RequestParam int venId,@RequestParam int agreeStatus);

}
