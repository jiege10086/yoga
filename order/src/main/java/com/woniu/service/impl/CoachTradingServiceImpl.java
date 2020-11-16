package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.domain.CoachTrading;
import com.woniu.mapper.CoachTradingMapper;
import com.woniu.service.CoachTradingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.utils.JSONResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-14
 */
@Service
public class CoachTradingServiceImpl extends ServiceImpl<CoachTradingMapper, CoachTrading> implements CoachTradingService {
    @Resource
    private CoachTradingMapper coachTradingMapper;


    //用户对教练进行评价
    @Override
    public void updateCaotarding(int caoId, int status) {
        QueryWrapper<CoachTrading> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("coa_id",caoId);
        CoachTrading coachTrading = coachTradingMapper.selectOne(queryWrapper);
        //好评
        if (status==0){
            coachTrading.setGoodPraise(coachTrading.getGoodPraise()+1);
        }
        if (status==1){
            coachTrading.setGeneralPraise(coachTrading.getGeneralPraise()+1);
        }
        if (status==2){
            coachTrading.setBadPraise(coachTrading.getBadPraise()+1);
        }
    }
}
