package com.woniu.service;

import com.woniu.domain.CoachTrading;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.utils.JSONResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-14
 */
public interface CoachTradingService extends IService<CoachTrading> {

    //用户对教练进行评价
    public void updateCaotarding(int caoId,int sataus);
}
