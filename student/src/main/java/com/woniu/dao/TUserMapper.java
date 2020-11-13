package com.woniu.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.domain.TUser;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lx
 * @since 2020-11-07
 */
public interface TUserMapper extends BaseMapper<TUser> {

    TUser searchUserByPhone(int uPhone);

    TUser searchUserByEmail(String uEmail);

    void registUserByPhone(int uPhone, String uPassword);

    void registUserByEmamil(String uEmail, String uPassword);

//    void perfectUserInfo(int uPhone, String uEMail, int uShowStatus, String uHeadPortrait, String uName, String uAddress, String uTruename, int uIdcard, int uQq);


    TUser userLoginByPhone(int uPhone, String uPassword);

    TUser userLoginByEmail(String uEMail, String uPassword);

    void perfectUserInfoByUEmail(Integer uPhone, String uEMail, Integer uShowStatus, String uHeadPortrait, String uName, String uAddress, String uTruename, Integer uIdcard, Integer uQq);

    void perfectUserInfoByUphone(Integer uPhone, String uEMail, Integer uShowStatus, String uHeadPortrait, String uName, String uAddress, String uTruename, Integer uIdcard, Integer uQq);

    TUser searchUserById(Integer uId);

    void addUserMoney(Integer uId, double uMoney);

    void updateUserMoney(Integer uId, double money);

    void updateUserAttention(Integer userId, String json);

    void updateUserVenAttention(Integer userId, String json);

    void updateUserUserAttention(Integer userId, String json);


    void addCoaClich(Integer coaId, String coaName);
}
