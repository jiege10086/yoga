package com.woniu.service.impl;


import com.woniu.dao.TMessageMapper;
import com.woniu.domain.TMessage;
import com.woniu.service.TMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TMessageServiceImpl extends ServiceImpl<TMessageMapper, TMessage> implements TMessageService {
    @Autowired
    private TMessageMapper tMessageMapper;
    @Override
    public void addUserCommit(Integer uId, String detail, Integer acceptId, String sendName) {
        tMessageMapper.addUserCommit(uId,detail,acceptId,sendName);
    }

    @Override
    public List<TMessage> showUserMessages(Integer uid) {
        return tMessageMapper.showUserMessages(uid);
    }
}
