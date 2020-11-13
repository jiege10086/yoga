package com.woniu.service.impl;


import com.woniu.dao.CoachTradingMapper;
import com.woniu.domain.CoachTrading;
import com.woniu.service.CoachTradingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lx
 * @since 2020-11-12
 */
@Service
public class CoachTradingServiceImpl extends ServiceImpl<CoachTradingMapper, CoachTrading> implements CoachTradingService {
    @Autowired
    private CoachTradingMapper coachTradingMapper;
    @Override
    public void addCoaClich(Integer coaId, Integer clinchSuccess) {
        coachTradingMapper.addCoaClich(coaId,clinchSuccess);
    }

    @Override
    public CoachTrading selectByCoaId(Integer coaId) {
        return coachTradingMapper.selectByCoaId(coaId);
    }

    @Override
    public void addNewCoaClich(Integer coaId, String coaName, int clisuccess) {
        coachTradingMapper.addNewCoaClich(coaId,coaName,clisuccess);
    }

    @Override
    public CoachTrading selectCoaTradings(Integer coaId) {
        return coachTradingMapper.selectCoaTradings(coaId);
    }

    @Override
    public void commitCoach(Integer coaId, Integer goodPraise1, Integer generalPraise1, Integer badPraise1) {
        coachTradingMapper.commitCoach(coaId,goodPraise1,generalPraise1,badPraise1);
    }

}
