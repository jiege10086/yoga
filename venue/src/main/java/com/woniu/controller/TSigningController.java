package com.woniu.controller;


import com.woniu.doParam.SignParam;
import com.woniu.domain.TSigning;
import com.woniu.service.TSigningService;
import com.woniu.utils.JSONResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @PostMapping("/insertsigning")
    public JSONResult insert(SignParam sig)throws Exception{
        TSigning ts = new TSigning();
        ts.setSendId(sig.getSendId());
        ts.setAcceptId(sig.getAcceptId());
        ts.setDetail(sig.getDetail());
        ts.setAgreeStatus(0);
        ts.setSendStatus(0);
        tSigningService.save(ts);
        return new JSONResult("200","success",null,null);
    }

}

