package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.doParam.CoachParam;
import com.woniu.domain.TCoach;
import com.woniu.service.TCoachService;
import com.woniu.utils.JSONResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/tCoach")
public class TCoachController {

    @Resource
    private TCoachService tCoachService;

    @GetMapping("/select")
    public JSONResult selectCoach(CoachParam co)throws Exception{
        Page<TCoach> tCoachPage = new Page<>(co.getPageSize(),co.getPageNum());
        QueryWrapper<TCoach> tc = new QueryWrapper<>();
        if(!StringUtils.isEmpty(co.getCoaName())) {
            tc.like("coa_name",co.getCoaName());
        }
        if(!StringUtils.isEmpty(co.getFactions())) {
            tc.like("factions",co.getFactions());
        }
        Page<TCoach> page = tCoachService.page(tCoachPage, tc);
        System.out.println(page.getTotal()+"-------------------");
        return new JSONResult("200","success",null,page);
    }
}

