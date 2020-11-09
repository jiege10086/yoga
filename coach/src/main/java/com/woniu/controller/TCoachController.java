package com.woniu.controller;


import com.woniu.domain.TCoach;
import com.woniu.param.CoaRegister;
import com.woniu.service.impl.TCoachServiceImpl;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import com.woniu.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

    //教练登录
    @RequestMapping("/coaLogin")
    public JSONResult coaLogin(HttpServletResponse response, String name, String password) throws Throwable {
        TCoach tCoach =tCoachService.CoachLogin(name, MD5Util.MD5EncodeUtf8(password));
        String token = JwtUtils.createToken(tCoach);
        response.setHeader("X-token",token);
        return new JSONResult("200","登陆成功",null,null);
    }
}

