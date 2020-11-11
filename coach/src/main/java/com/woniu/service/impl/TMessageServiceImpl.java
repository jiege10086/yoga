package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.domain.TMessage;
import com.woniu.domain.TOrder;
import com.woniu.dto.CoaMessageDto;
import com.woniu.mapper.TMessageMapper;
import com.woniu.param.CoaMessageParam;
import com.woniu.service.TMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-10
 */
@Service
public class TMessageServiceImpl extends ServiceImpl<TMessageMapper, TMessage> implements TMessageService {
    @Resource
    private TMessageMapper tMessageMapper;

    //教练查看自己的留言
    @Override
    public PageInfo<CoaMessageDto> selectMessageByCoaId(int coaId, int pageSize, int pageIndex, String status) {
        PageHelper.startPage(pageIndex, 10);
        QueryWrapper<TMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accept_id",coaId);
        queryWrapper.eq("peo_role",1);
        if(status==null){
            queryWrapper.eq("read_status",status);
        }
        List<TMessage> tMessages = tMessageMapper.selectList(queryWrapper);
        List<CoaMessageDto> dtos = new ArrayList<CoaMessageDto>();
        for(TMessage tMessage:tMessages){
            CoaMessageDto coaMessageDto = new CoaMessageDto();
            BeanUtils.copyProperties(tMessage,coaMessageDto);
            dtos.add(coaMessageDto);
        }
        PageInfo<CoaMessageDto> pageinfo=new PageInfo<CoaMessageDto>(dtos);
        return pageinfo;
    }

    //教练新增一条留言
    @Override
    public void insertMessage(CoaMessageParam coaMessageParam) {
        TMessage tMessage = new TMessage();
        BeanUtils.copyProperties(coaMessageParam,tMessage);
        tMessageMapper.insert(tMessage);
    }
}
