package com.woniu.controller;


import com.woniu.doParam.VenRecruitmentParam;
import com.woniu.domain.VenueRecruitment;
import com.woniu.service.VenueRecruitmentService;
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
@RequestMapping("/venueRecruitment")
public class VenueRecruitmentController {
    @Resource
    private VenueRecruitmentService venueRecruitmentService;

    @PostMapping("/insertRecruitment")
    public JSONResult insertRecruit(VenRecruitmentParam vrt)throws Exception{
        VenueRecruitment build = VenueRecruitment.builder().venId(vrt.getVenId()).detail(vrt.getDetail())
                .factions(vrt.getFactions()).money(vrt.getMoney()).time(vrt.getTime()).status(0).build();
        venueRecruitmentService.save(build);
        return new JSONResult("200","success",null,null);
    }
}

