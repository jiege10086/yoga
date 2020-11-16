package com.woniu.client;

import com.woniu.param.CoaSelectParam;
import com.woniu.service.TCoachService;
import com.woniu.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CoachClient")
public class CoachClient {
    @Autowired
    private TCoachService tCoachService;

    //学员模糊查询教练
    @RequestMapping("/selectCoach")
    public JSONResult selectCoach(CoaSelectParam coaSelectParam){
        return new JSONResult("200","查询成功",null,tCoachService.selectCoach(coaSelectParam));
    }

    //根据id查询教练
    @GetMapping
    public JSONResult selectCoachById(int coaId) throws Exception{
        return new JSONResult("200","查询成功",null,tCoachService.selectCoachById(coaId));
    }
}
