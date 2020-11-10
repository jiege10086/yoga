package com.woniu.controller;


import com.woniu.doParam.CourseParam;
import com.woniu.domain.TCourse;
import com.woniu.service.TCourseService;
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
@RequestMapping("/tCourse")
public class TCourseController {

    @Resource
    private TCourseService tCourseService;

    @PostMapping("insertCourse")
    public JSONResult insertCourse(CourseParam cp)throws Exception{
        TCourse build = TCourse.builder().coaName(cp.getCoaName()).couName(cp.getCouName()).couDetail(cp.getCouDetail())
                .coaId(cp.getCoaId()).couTime(cp.getCouTime()).venId(cp.getVenId()).venName(cp.getVenName()).status(0).build();

        tCourseService.save(build);
        return new JSONResult("200","success",null,null);
    }
}

