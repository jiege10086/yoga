package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.domain.VenueProducts;
import com.woniu.service.VenueProductsService;
import com.woniu.utils.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/venueProducts")
public class VenueProductsController {

    @Resource
    private VenueProductsService venueProductsService;

    @GetMapping("/select")
    public JSONResult SselectProduct(Integer VenId)throws Exception{

        QueryWrapper<VenueProducts> vp = new QueryWrapper<>();
        vp.eq("ven_id",VenId);
        List<VenueProducts> list = venueProductsService.list(vp);
        return new JSONResult("200","success",list,null);
    }
}

