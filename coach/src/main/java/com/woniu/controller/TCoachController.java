package com.woniu.controller;


import com.woniu.param.CoaRegister;
import com.woniu.service.impl.TCoachServiceImpl;
import com.woniu.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/coaRegister")
    public JSONResult coaRegister(@RequestBody CoaRegister coaRegister) throws Exception{
        tCoachService.insertCoach(coaRegister);
        return new JSONResult("200","注册成功",null,null);
    }

    @GetMapping
    public void selectCoachById(int coaId) throws Exception{
        tCoachService.selectCoachById();
    }
}

