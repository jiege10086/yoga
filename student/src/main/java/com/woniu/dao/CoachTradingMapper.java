package com.woniu.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.domain.CoachTrading;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lx
 * @since 2020-11-12
 */
public interface CoachTradingMapper extends BaseMapper<CoachTrading> {

    void addCoaClich(Integer coaId, Integer clinchSuccess);

    CoachTrading selectByCoaId(Integer coaId);

    void addNewCoaClich(Integer coaId, String coaName, int clisuccess);

    CoachTrading selectCoaTradings(Integer coaId);

    void commitCoach(Integer coaId, Integer goodPraise1, Integer generalPraise1, Integer badPraise1);
}
