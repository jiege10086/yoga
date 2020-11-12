package com.woniu.controller;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domain.TVenues;
import com.woniu.doparam.VenDoParam;
import com.woniu.service.TVenuesService;
import com.woniu.utils.JSONResult;
import com.woniu.utils.MD5Util;
import com.woniu.utils.UUIDUtil;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zly
 * @since 2020-11-11
 */
@RestController
@RequestMapping("/tVenues")
public class TVenuesController {

    @Resource
    private TVenuesService tVenuesService;
    /*
    * 分页查看场馆
    * */
    @GetMapping("selectAll")
    public JSONResult findAll(VenDoParam vd)throws Exception{
        Page<TVenues> tVenuesPage = new Page<>(vd.getPageSize(),vd.getPageNum());
        QueryWrapper<TVenues> tVenuesQueryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(vd.getVenName())) {
            tVenuesQueryWrapper.like("ven_name", vd.getVenName()).eq("ven_status",0);
        }
        Page<TVenues> page = tVenuesService.page(tVenuesPage,tVenuesQueryWrapper);
        return new JSONResult("200","success",null,page);
    }
    /*
    * 新增场馆
    * */
    @PostMapping("insertVen")
    public JSONResult insertVens(VenDoParam vdp, MultipartFile file)throws Exception{
        TVenues build = TVenues.builder().venId(UUIDUtil.getOrderNo()).venAddress(vdp.getVenAddress()).venBulk(vdp.getVenBulk())
                .venDetail(vdp.getVenDetail()).venEmail(vdp.getVenEmail()).venName(vdp.getVenName()).venPhone(vdp.getVenPhone())
                .venStatus(0).venPassword(MD5Util.MD5EncodeUtf8(vdp.getVenPassword())).build();

        boolean save = tVenuesService.save(build);

        if(save){
            tVenuesService.insertImg(build,file);
        }
        return new JSONResult("200","success",null,null);
    }

    @RequestMapping("updateVen")
    public JSONResult updateVen(VenDoParam vdp)throws Exception{
        UpdateWrapper<TVenues> tVenuesUpdateWrapper = new UpdateWrapper<>();
        tVenuesUpdateWrapper.eq("ven_id",vdp.getVenId()).set("ven_password",MD5Util.MD5EncodeUtf8(vdp.getVenPassword()));
        tVenuesService.update(tVenuesUpdateWrapper);

        return new JSONResult("200","success",null,null);
    }

    @PutMapping("removeVen")
    public JSONResult removeVen(VenDoParam vd)throws Exception{
        UpdateWrapper<TVenues> tVenuesUpdateWrapper = new UpdateWrapper<>();
        tVenuesUpdateWrapper.eq("ven_id",vd.getVenId()).set("ven_status",1);
        tVenuesService.update(tVenuesUpdateWrapper);
        return new JSONResult("200","success",null,null);
    }
}

