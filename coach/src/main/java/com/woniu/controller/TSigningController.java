package com.woniu.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.domain.TSigning;
import com.woniu.dto.CoaDtoToken;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.service.TSigningService;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/tSigning")
public class TSigningController {
    @Resource
    private TSigningService tSigningService;

    //对场馆发起签约请求
    @RequestMapping("/insertSigningByCoa")
    public JSONResult insertSigningByCoa(@RequestHeader("X-token")String token, int venId, String detail) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        tSigningService.insertSigningByCoa(Integer.parseInt(coach.getCoaId()),venId,detail);
        return new JSONResult("200","发起成功",null,null);
    }

    //查询自己的签约场馆信息
    @RequestMapping("/selectSigningByCoa")
    public  JSONResult selectSigningByCoa(@RequestHeader("X-token")String token, int agreeStatus, int status, int pageSize, int pageIndex) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        System.out.println(tSigningService
                .selectSigningByCoa(Integer.parseInt(coach.getCoaId()),agreeStatus,status,pageSize,pageIndex));
        return new JSONResult("200","查询成功",null,tSigningService
                .selectSigningByCoa(Integer.parseInt(coach.getCoaId()),agreeStatus,status,pageSize,pageIndex));
    }

    //教练发起请求同意或者拒绝
    @RequestMapping("/updateSigningByCoa")
    public void updateSigningByCoa(@RequestHeader("X-token")String token,int sigId,int venId,int agreeStatus) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        tSigningService.updateSigningByCoa(Integer.parseInt(coach.getCoaId()),sigId,venId,agreeStatus);
    }
}

