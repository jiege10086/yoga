package com.woniu.service;

import com.github.pagehelper.PageInfo;
import com.woniu.domain.TCoach;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.dto.CoachDto;
import com.woniu.param.CoaMessageParam;
import com.woniu.param.CoaRegister;
import com.woniu.param.CoaSelectParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-07
 */
public interface TCoachService extends IService<TCoach> {
    void insertCoach(CoaRegister coaRegister) throws Exception;

    TCoach selectCoachById(int coaId) throws Exception;

    TCoach CoachLogin(String name,String password) throws  Exception;

    void updateCoach(TCoach tCoach) throws Exception;

    String newPassword(String coaName,Integer status) throws Exception;

    void newPasswordLogin(String coaName,String password) throws  Exception;

    void coaGetMoney(Integer bankcard,Double money,Integer coaId)throws  Exception;

    void insertAttention(Integer coaId,Integer peoId,Integer role);

    PageInfo<CoachDto> selectCoach(CoaSelectParam coaSelectParam);
}
