package com.woniu.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.woniu.commons.JsonResult;
import com.woniu.config.TMessageClient;
import com.woniu.dto.*;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.param.CoaMessageParam;
import com.woniu.service.TMessageService;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lx
 * @since 2020-11-16
 */
@RestController
@RequestMapping("/message")
public class TMessageController implements TMessageClient{
    @Autowired
    private TMessageService tMessageService;
    @Resource
    private TMessageClient tMessageClient;

    //教练查询给自己的留言
    @RequestMapping("/selectCoaMessage")
    private JSONResult selectCoaMessage(@RequestHeader("X-token") String token,int pageIndex,int pageSize,String status) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }

        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        PageInfo<CoaMessageDto> pageInfo = tMessageService.selectMessageByCoaId(Integer.parseInt(coach.getCoaId()), pageSize, pageIndex, status);
        return new JSONResult("200","查询成功",null,pageInfo);
    }

    //教练新增留言
    @RequestMapping("/coainsertMessage")
    private JSONResult insertMessage(@RequestHeader("X-token") String token,String coaMessageParam) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        CoaMessageParam param = JSONObject.parseObject(coaMessageParam, CoaMessageParam.class);
        param.setPeoRole(1);
        tMessageService.insertMessage(param,coach);
        return new JSONResult("200","新增成功",null,null);
    }

    /**
     * 查看我的留言
     */
    @GetMapping("/showUserMessage")
    public JsonResult showUserMessages(@RequestHeader("X-token") String token,int pageIndex,int pageSize) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        TUserDtoToken tUserDtoToken = JwtUtils.parseToken(token, TUserDtoToken.class);
        String uId = tUserDtoToken.getuId();
        PageInfo<MessageDto> tMessages = tMessageService.showUserMessages(Integer.parseInt(uId),pageIndex,pageSize);
        return new JsonResult(200,"success",tMessages,null);
    }

    /**
     * 用户添加教练留言
     */
    @PostMapping("/addUserCommit")
    public JsonResult addUserCommit(@RequestHeader("X-token") String token,String detail, String acceptId) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        TUserDtoToken tUserDtoToken = JwtUtils.parseToken(token, TUserDtoToken.class);
        Integer uId=Integer.parseInt(tUserDtoToken.getuId());
        TUser tUser = tMessageClient.searchUserById(uId);
        String sendName = tUser.getUName();
        tMessageService.addUserCommit(uId,detail,acceptId,sendName);
        return new JsonResult(200,"success",null,null);
    }

    @Override
    public TUser searchUserById(int uId) throws NumberNotFoundException {
        TUser tUser = tMessageClient.searchUserById(uId);
        return tUser;
    }
}

