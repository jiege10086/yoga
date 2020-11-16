package com.woniu.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.doParam.SignParam;
import com.woniu.domain.TCoach;
import com.woniu.domain.TSigning;
import com.woniu.domain.TVenues;
import com.woniu.dto.CoaDtoToken;
import com.woniu.dto.VenDotToken;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.service.TCoachService;
import com.woniu.service.TSigningService;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-16
 */
@RestController
@RequestMapping("/tSigning")
public class TSigningController {
    @Resource
    private TSigningService tSigningService;

    @Resource
    private TCoachService tCoachService;

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

    /*
     * 同意教练签约场馆
     * */
    @RequestMapping("/updateSig")
    public JSONResult updateSig(@RequestHeader("X-token") String token,Integer coaId) throws Throwable {
        if(token==null){
            return new JSONResult("500","权限不足",null,null);
        }
        VenDotToken venDotToken = JwtUtils.parseToken(token, VenDotToken.class);
        TVenues build = TVenues.builder().venId(Integer.parseInt(venDotToken.getVenId())).build();
        UpdateWrapper<TSigning> tSigningUpdateWrapper = new UpdateWrapper<>();
        tSigningUpdateWrapper.eq("send_id",coaId).eq("accept_id",build.getVenId()).set("agree_status",1);
        boolean update = tSigningService.update(tSigningUpdateWrapper);
        if(update){
            TCoach byId = tCoachService.getById(coaId);
            System.out.println(byId.getBelongsVenues());
            if(byId.getBelongsVenues()==null||byId.getBelongsVenues().equals("")){
                ArrayList<Integer> list = new ArrayList<>();
                list.add(build.getVenId());
                String s = JSON.toJSON(list).toString();
                System.out.println(s);
                UpdateWrapper<TCoach> coachUpdateWrapper = new UpdateWrapper<>();
                coachUpdateWrapper.eq("coa_id",coaId).set("belongs_venues",s);
                tCoachService.update(coachUpdateWrapper);
            }else {
                String belongsVenues = byId.getBelongsVenues();
                List<Integer> integers = JSON.parseArray(belongsVenues, Integer.class);
                integers.add(build.getVenId());
                String s = JSON.toJSON(integers).toString();
                UpdateWrapper<TCoach> coachUpdateWrapper = new UpdateWrapper<>();
                coachUpdateWrapper.eq("coa_id",coaId).set("belongs_venvues",s);
                tCoachService.update(coachUpdateWrapper);
            }
        }
        return new JSONResult("200","success",null,null);
    }
}

