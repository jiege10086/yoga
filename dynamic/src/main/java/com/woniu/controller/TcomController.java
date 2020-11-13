package com.woniu.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.dto.DynamicDto;
import com.woniu.param.DynamicParam;
import com.woniu.service.TDynamicService;
import com.woniu.utils.JSONResult;
import org.springframework.web.bind.annotation.RequestBody;
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
 * @since 2020-11-11
 */
@RestController
@RequestMapping("/tcom")
public class TcomController {
    @Resource
    private TDynamicService tDynamicService;

    //根据id查询动态
    @RequestMapping("/selectDynById")
    public JSONResult selectDynById(int id, int pageSize, int pageIndex){
        PageInfo<DynamicDto> dynamicDtoPageInfo = tDynamicService.selectDynById(id, pageSize, pageIndex);
        return new JSONResult("200","查询成功",null,dynamicDtoPageInfo);
    }

    //根据自己的id查询我的关注动态信息
    @RequestMapping("/selectDynMyId")
    public JSONResult selectDynMyId(int uuid, int pageSize, int pageIndex,int role){
        PageInfo<DynamicDto> dynamicDtoPageInfo = tDynamicService.selectDynMyId(uuid, pageSize, pageIndex,role);
        return new JSONResult("200","查询成功",null,dynamicDtoPageInfo);
    }

    //新增一条动态信息
    @RequestMapping("/insertDynByUuid")
    public JSONResult insertDynByUuid(@RequestBody DynamicParam dynamicParam){
        tDynamicService.insertDynByUuid(dynamicParam);
        return new JSONResult("200","查询成功",null,null);
    }

    //判断是否好友
    @RequestMapping("/friend")
    public boolean friend(int selectId,int accpetId){
        return tDynamicService.friend(selectId,accpetId);
    }
}

