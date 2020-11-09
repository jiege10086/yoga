package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.doParam.VenDoParam;
import com.woniu.domain.TVenues;
import com.woniu.service.TVenuesService;
import com.woniu.utils.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zly
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/tVenues")
public class TVenuesController {

    @Resource
    private TVenuesService tVenuesService;
    @GetMapping("/login")
    public JSONResult select(VenDoParam ven)throws Exception{
        QueryWrapper<TVenues> tVenuesQueryWrapper = new QueryWrapper<>();
        tVenuesQueryWrapper.eq("ven_phone",ven.getVenPhone()).eq("ven_password",ven.getVenPassword());
        TVenues vens = tVenuesService.getOne(tVenuesQueryWrapper);
        return new JSONResult("200","success",null,vens);
    }


    @RequestMapping("/updateVenue")
    public JSONResult update( VenDoParam v, MultipartFile file)throws Exception{
        UpdateWrapper<TVenues> tVenuesUpdateWrapper = new UpdateWrapper<>();
        tVenuesUpdateWrapper.eq("ven_id",v.getVenId()).set("ven_address",v.getVenAddress()).set("ven_name",v.getVenName()).set("ven_detail",v.getVenDetail()).set("ven_bulk",v.getVenBulk());
        boolean update = tVenuesService.update(tVenuesUpdateWrapper);

        tVenuesService.updateImg(v,file);
        return new JSONResult("200","success",null,null);
    }
}

