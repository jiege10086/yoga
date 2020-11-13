package com.woniu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.domain.CoachTrading;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lx
 * @since 2020-11-12
 */
public interface CoachTradingService extends IService<CoachTrading> {

    void addCoaClich(Integer coaId,Integer clinchSuccess);

    CoachTrading selectByCoaId(Integer coaId);

    void addNewCoaClich(Integer coaId, String coaName, int clisuccess);

    CoachTrading selectCoaTradings(Integer coaId);

    void commitCoach(Integer coaId, Integer goodPraise1, Integer generalPraise1, Integer badPraise1);
}
