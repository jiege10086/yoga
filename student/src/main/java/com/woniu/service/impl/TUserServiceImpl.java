package com.woniu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.dao.TUserMapper;
import com.woniu.domain.TCoach;
import com.woniu.domain.TUser;
import com.woniu.dto.CoaAddressDtoList;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private RedisTemplate<String, Object> rt;

    @Resource
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

    @Override
    public TUser searchUserById(Integer uId) {
        TUser tUser = tUserMapper.searchUserById(uId);
        return tUser;
    }

    @Override
    public void addUserMoney(Integer uId, double uMoney) {
        tUserMapper.addUserMoney(uId,uMoney);
    }

    @Override
    public void updateUserMoney(Integer uId, double money) {
        tUserMapper.updateUserMoney(uId,money);
    }

    @Override
    public void updateUserAttention(Integer userId, String json) {
        tUserMapper.updateUserAttention(userId,json);
    }

    @Override
    public void updateUserVenAttention(Integer userId, String json) {
        tUserMapper.updateUserVenAttention(userId,json);
    }

    @Override
    public void updateUserUserAttention(Integer userId, String json) {
        tUserMapper.updateUserUserAttention(userId,json);
    }

    @Override
    public List<CoaAddressDtoList> selectCoaByAddress(double longitude, double latitude, double fanwei) {
        //放入半径和距离
        Circle circle = new Circle(new Point(longitude,latitude),fanwei*1000);
        //获得满足条件的教练名称对象
        GeoResults<RedisGeoCommands.GeoLocation<Object>> coaAddress = rt.opsForGeo().radius("coaAddress", circle);
        //获得经度和纬度满足的条件对象
        List<GeoResult<RedisGeoCommands.GeoLocation<Object>>> content = coaAddress.getContent();
        //新建list放坐标对象
        ArrayList<CoaAddressDtoList> list = new ArrayList<>();
        for(GeoResult geoResult:content) {
            //切割符合条件的id
            String conten = geoResult.getContent().toString();
            int douhao = conten.lastIndexOf(",");
            int denghao = conten.indexOf("=");
            String truename = conten.substring(denghao + 1, douhao);
            //取出id的经纬度
            List<Point> points = rt.opsForGeo().position("coaAddress", Integer.parseInt(truename));
            Point coapoint = points.get(0);
            //放入对象中
            CoaAddressDtoList coaAddressDto = new CoaAddressDtoList();
            coaAddressDto.setLongitude(coapoint.getX());
            coaAddressDto.setLatitude(coapoint.getY());
            coaAddressDto.setCoaId(truename);
            list.add(coaAddressDto);
        }
        return list;
    }

    @Override
    public String newPassword(String uName) {
        String password = UUID.randomUUID().toString().substring(0, 6);
        rt.opsForValue().set("newpassword"+uName,password,30*60, TimeUnit.SECONDS);
        return password;
    }

    @Override
    public void newPasswordLogin(String uName, String password) throws NumberNotFoundException {
        Object newpassword = rt.opsForValue().get("newpassword" + uName);
        System.out.println(newpassword);
        System.out.println(password);
        if(newpassword==null){
            throw new NumberNotFoundException("密码已过期");
        }
        if(!(password.equals(newpassword.toString()))){
            throw new NumberNotFoundException("密码不正确");
        }
        QueryWrapper<TUser> tUserQueryWrapper = new QueryWrapper<>();
        tUserQueryWrapper.eq("user_name", uName);
        TUser tUser = tUserMapper.selectOne(tUserQueryWrapper);
        tUser.setUPassword(MD5Util.MD5EncodeUtf8(newpassword.toString()));
        tUserMapper.updateById(tUser);
    }

}



