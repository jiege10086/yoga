package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domain.TCoach;
import com.woniu.doparam.CoachParam;
import com.woniu.service.TCoachService;
import com.woniu.utils.JSONResult;
import com.woniu.utils.MD5Util;
import com.woniu.utils.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/tCoach")
public class TCoachController {

    @Resource
    private TCoachService tCoachService;
    /*
    * 分页查看教练
    * */
    @GetMapping("/selectAll")
    public JSONResult findAll(CoachParam cp)throws Exception{
        Page<TCoach> tCoachPage = new Page<>(cp.getPageSize(),cp.getPageNum());
        QueryWrapper<TCoach> tCoachQueryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(cp.getFactions())){
            tCoachQueryWrapper.like("factions",cp.getFactions());
        }
        if(!StringUtils.isEmpty(cp.getTruename())){
            tCoachQueryWrapper.eq("truename",cp.getTruename());
        }
        Page<TCoach> page = tCoachService.page(tCoachPage, tCoachQueryWrapper);
        return new JSONResult("200","success",null,page);
    }
    /*
    * 添加教练
    * */
    @PostMapping("insertCoach")
    public JSONResult insertCoach(CoachParam coachParam)throws Exception{
        TCoach build = TCoach.builder().coaPhone(coachParam.getCoaPhone()).coaPassword(MD5Util.MD5EncodeUtf8(coachParam.getCoaPassword()))
                .coaId(UUIDUtil.getOrderNo()).certificationStatus(0).build();
        tCoachService.save(build);
        return new JSONResult("200","success",null,null);
    }
    /*
    * 修改教练
    * */
    @PutMapping("/updateCoach")
    public JSONResult updateCoach(CoachParam coachParam)throws Exception{
        UpdateWrapper<TCoach> tCoachUpdateWrapper = new UpdateWrapper<>();
        tCoachUpdateWrapper.eq("coa_id",coachParam.getCoaId()).set("coa_phone",coachParam.getCoaPhone()).set("price",coachParam.getPrice())
                .set("factions",coachParam.getFactions()).set("coa_name",coachParam.getCoaName())
                .set("coa_address",coachParam.getCoaAddress()).set("coa_email",coachParam.getCoaEmail());
        tCoachService.update(tCoachUpdateWrapper);
        return new JSONResult("200","success",null,null);
    }
    /*
    * 删除教练（伪删除）
    * */
    @PutMapping("/removeCoach")
    public JSONResult removeCoach(CoachParam cp)throws Exception{
        UpdateWrapper<TCoach> tCoachUpdateWrapper = new UpdateWrapper<>();
        tCoachUpdateWrapper.eq("coa_id",cp.getCoaId()).set("certification_status",1);
        tCoachService.update(tCoachUpdateWrapper);
        return new JSONResult("200","success",null,null);
    }
}

