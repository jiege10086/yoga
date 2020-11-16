package com.woniu.fen;

import com.woniu.param.DynamicParam;
import com.woniu.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("dynamic")
public interface DynamicFen {
    //查询自己的动态信息
    @RequestMapping("/tcom/selectDynById")
    public JSONResult selectDynById(@RequestParam int id,@RequestParam int pageSize,@RequestParam int pageIndex);

    //根据自己的id查询我的关注动态信息
    @RequestMapping("/tcom/selectDynMyId")
    public JSONResult selectDynMyId(@RequestParam int uuid,@RequestParam int pageSize,@RequestParam int pageIndex,@RequestParam int role);

    //新增一条动态信息
    @RequestMapping("/tcom/insertDynByUuid")
    public JSONResult insertDynByUuid(@RequestBody DynamicParam dynamicParam);
}
