package com.woniu.controller;


import com.woniu.service.impl.TCoachServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/tCoach")
public class TCoachController {
    @Autowired
    private TCoachServiceImpl tCoachService;

    @GetMapping
    public void selectCoachById(int coaId){
        tCoachService.selectCoachById();
    }
}

