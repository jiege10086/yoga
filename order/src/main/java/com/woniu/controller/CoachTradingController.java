package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.domain.CoachTrading;
import com.woniu.service.CoachTradingService;
import com.woniu.utils.JSONResult;
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
 * @since 2020-11-14
 */
@RestController
@RequestMapping("/coachTrading")
public class CoachTradingController {
    @Resource
    private CoachTradingService coachTradingService;

    //用户对教练进行评价
    @RequestMapping("/updateCaotarding")
    public JSONResult   updateCaotarding(int caoId, int status) {
        coachTradingService.updateCaotarding(caoId,status);
        return new JSONResult("200","评价成功",null,null);
    }
}

