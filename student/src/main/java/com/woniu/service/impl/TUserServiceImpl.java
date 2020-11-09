package com.woniu.service.impl;


import com.woniu.dao.TUserMapper;
import com.woniu.domain.TUser;
import com.woniu.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lx
 * @since 2020-11-07
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {
    @Autowired
    private TUserMapper tUserMapper;
    @Override
    public TUser searchUserByPhone(int uPhone) {
        TUser tUser = tUserMapper.searchUserByPhone(uPhone);
        return tUser;
    }

    @Override
    public TUser searchUserByEmail(String uEmail) {
        TUser tUser = tUserMapper.searchUserByEmail(uEmail);
        return tUser;
    }

    @Override
    public void registUserByPhone(int uPhone, String uPassword) {
        tUserMapper.registUserByPhone(uPhone,uPassword);
    }

    @Override
    public void registUserByEmamil(String uEmail, String uPassword) {
        tUserMapper.registUserByEmamil(uEmail,uPassword);
    }

    @Override
    public TUser userLoginByPhone(int uPhone, String uPassword) {
        TUser tUser = tUserMapper.userLoginByPhone(uPhone, uPassword);
        return tUser;
    }

    @Override
    public TUser userLoginByEmail(String uEMail, String uPassword) {
        TUser tUser = tUserMapper.userLoginByEmail(uEMail, uPassword);
        return tUser;
    }

    @Override
    public void perfectUserInfoByUEmail(Integer uPhone, String uEMail, Integer uShowStatus, String uHeadPortrait, String uName, String uAddress, String uTruename, Integer uIdcard, Integer uQq) {
        tUserMapper.perfectUserInfoByUEmail(uPhone,uEMail,uShowStatus,uHeadPortrait,uName,uAddress,uTruename,uIdcard,uQq);
    }

    @Override
    public void perfectUserInfoByUphone(Integer uPhone, String uEMail, Integer uShowStatus, String uHeadPortrait, String uName, String uAddress, String uTruename, Integer uIdcard, Integer uQq) {
        tUserMapper.perfectUserInfoByUphone(uPhone,uEMail,uShowStatus,uHeadPortrait,uName,uAddress,uTruename,uIdcard,uQq);
    }
}



