package com.woniu.controller;


import com.woniu.dto.CoaDtoToken;
import com.woniu.dto.VenusDto;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.fen.DynamicFen;
import com.woniu.service.TVenuesService;
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
 * @since 2020-11-19
 */
@RestController
@RequestMapping("/tVenues")
public class TVenuesController {
    @Resource
    private TVenuesService tVenuesService;

    @Resource
    private DynamicFen dynamicFen;

    //主页教练
    @RequestMapping("/selectVenues")
    private JSONResult selectVenues( String name, int pageSize, int pageIndex){
        return new JSONResult("200","查询成功",null,tVenuesService.selectVenues(name, pageSize, pageIndex));
    }

    //查询单个场馆
    @RequestMapping("/selectVenuesById")
    private JSONResult selectVenuesById(@RequestHeader String token,int venId) throws Throwable {
        if (token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        VenusDto venusDto = tVenuesService.selectVenues(venId);
        return new JSONResult("200","查询成功",null,venusDto);
    }

}

