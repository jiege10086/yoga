package com.woniu.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.woniu.dto.CoaDtoToken;
import com.woniu.dto.CoaMessageDto;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.fen.MessageFen;
import com.woniu.param.CoaMessageParam;
import com.woniu.service.TMessageService;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/tMessage")
public class TMessageController {
    @Resource
    private MessageFen messageFen;

    //教练查询给自己的留言
    @RequestMapping("/selectCoaMessage")
    private JSONResult selectCoaMessage(@RequestHeader("X-token") String token,int pageIndex,int pageSize,String status) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        JSONResult pageInfo = messageFen.selectCoaMessage( pageSize, pageIndex, status);
        return pageInfo;
    }

    //教练新增留言
    @RequestMapping("/coainsertMessage")
    private JSONResult insertMessage(@RequestHeader("X-token") String token,CoaMessageParam coaMessageParam) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        System.out.println(coaMessageParam.getDetail());
        //CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        coaMessageParam.setPeoRole(1);
        String jsonString = JSONObject.toJSONString(coaMessageParam);
        messageFen.insertMessage(jsonString);
        return new JSONResult("200","新增成功",null,null);
    }
}

