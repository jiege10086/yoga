package com.woniu.fen;

import com.woniu.param.DynamicParam;
import com.woniu.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient("publicinterface")
public interface PublicIntegerFen {

    @RequestMapping("/tcom/selectDynById")
    public JSONResult selectDynById(int id, int pageSize, int pageIndex);

    //根据自己的id查询我的关注动态信息
    @RequestMapping("/tcom/selectDynMyId")
    public JSONResult selectDynMyId(int uuid, int pageSize, int pageIndex,int role);

    //新增一条动态信息
    @RequestMapping("/insertDynByUuid")
    public JSONResult insertDynByUuid(@RequestBody DynamicParam dynamicParam);

    @RequestMapping("/friend")
    public boolean friend(int selectId,int accpetId);

}
