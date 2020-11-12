package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.doParam.SignParam;
import com.woniu.domain.TSigning;
import com.woniu.domain.TVenues;
import com.woniu.dot.VenDotToken;
import com.woniu.service.TSigningService;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zly
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/tSigning")
public class TSigningController {

    @Resource
    private TSigningService  tSigningService;
    /*
    * 签约教练
    *
    * */
    @PostMapping("/insertsigning")
    public JSONResult insert(SignParam sig)throws Exception{
        TSigning ts = new TSigning();
        ts.setSendId(sig.getSendId());
        ts.setAcceptId(sig.getAcceptId());
        ts.setDetail(sig.getDetail());
        ts.setAgreeStatus(0);
        ts.setSendStatus(1);
        tSigningService.save(ts);
        return new JSONResult("200","success",null,null);
    }
    /*
    * 查询教练签约场馆
    * */
    @GetMapping("/selectSig")
    public JSONResult selectAll(@RequestHeader("X-token") String token) throws Throwable {
        if(token==null){
            return new JSONResult("500","权限不足",null,null);
        }
        VenDotToken venDotToken = JwtUtils.parseToken(token, VenDotToken.class);
        TVenues build = TVenues.builder().venId(Integer.parseInt(venDotToken.getVenId())).build();
        QueryWrapper<TSigning> signingQueryWrapper = new QueryWrapper<>();
        signingQueryWrapper.eq("accept_id",build.getVenId()).eq("send_status",0);
        List<TSigning> list = tSigningService.list(signingQueryWrapper);
        return new JSONResult("200","success",list,null);
    }
}

