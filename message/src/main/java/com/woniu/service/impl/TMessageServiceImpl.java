package com.woniu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.dao.TMessageMapper;
import com.woniu.domain.TMessage;
import com.woniu.dto.CoaDtoToken;
import com.woniu.dto.CoaMessageDto;
import com.woniu.dto.MessageDto;
import com.woniu.param.CoaMessageParam;
import com.woniu.service.TMessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lx
 * @since 2020-11-12
 */
@Service
@Primary
public class TMessageServiceImpl extends ServiceImpl<TMessageMapper, TMessage> implements TMessageService {
    @Resource
    private TMessageMapper tMessageMapper;
    @Override
    public void addUserCommit(Integer uId, String detail, String acceptId, String sendName) {
        TMessage tMessage = new TMessage();
        tMessage.setSendId(uId);
        tMessage.setDetail(detail);
        tMessage.setAcceptId(acceptId);
        tMessage.setSendName(sendName);
        tMessageMapper.insert(tMessage);
    }

    //教练查看自己的留言
    @Override
    public PageInfo<CoaMessageDto> selectMessageByCoaId(int coaId, int pageSize, int pageIndex, String status) {

        QueryWrapper<TMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accept_id",coaId);
        queryWrapper.eq("peo_role",1);
        if(status!=null){
            queryWrapper.eq("read_status",status);
        }
        PageHelper.startPage(pageIndex,pageSize);
        System.out.println(pageIndex+":"+pageSize);
        List<TMessage> tMessages = tMessageMapper.selectList(queryWrapper);
        PageInfo<TMessage> pageinfo=new PageInfo<TMessage>(tMessages);
        PageInfo<CoaMessageDto> newpageinfo=new PageInfo<CoaMessageDto>();
        BeanUtils.copyProperties(pageinfo,newpageinfo);
        return newpageinfo;
    }

    //教练新增一条留言
    @Override
    public void insertMessage(CoaMessageParam coaMessageParam, CoaDtoToken coaDtoToken) {
        TMessage tMessage = new TMessage();
        System.out.println(coaMessageParam);
        BeanUtils.copyProperties(coaMessageParam,tMessage);
        tMessage.setSendId(Integer.parseInt(coaDtoToken.getCoaId()));
        tMessage.setReadStatus("未读");
        tMessage.setSendName(coaDtoToken.getCoaName());

        tMessageMapper.insert(tMessage);
    }

    @Override
    public PageInfo<MessageDto> showUserMessages(int uId, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        QueryWrapper<TMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("send_id",uId);
        List<TMessage> tMessages = tMessageMapper.selectList(queryWrapper);
        PageInfo<TMessage> pageinfo=new PageInfo<TMessage>(tMessages);
        PageInfo<MessageDto> newpageinfo=new PageInfo<MessageDto>();
        BeanUtils.copyProperties(pageinfo,newpageinfo);
        return newpageinfo;
    }
}
