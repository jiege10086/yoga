package com.woniu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.domain.TCoach;
import com.woniu.domain.TUser;
import com.woniu.dto.CoaAddressDtoList;
import com.woniu.dto.CoaUserDto;
import com.woniu.dto.CoachDto;
import com.woniu.mapper.TCoachMapper;
import com.woniu.mapper.TUserMapper;
import com.woniu.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
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

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-07
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {
    @Resource
    private TUserMapper tUserMapper;

    @Resource
    private TCoachMapper tCoachMapper;

    @Autowired
    private RedisTemplate<String, Object> rt;

    //查询教练自己的学员
    @Override
    public PageInfo<CoaUserDto> selectUserByCoa(int coaId, int pageSize, int pageIndex) {
        TCoach coach = tCoachMapper.selectById(coaId);
        String UserJSON=coach.getMyUser();
        List<Integer> users = JSON.parseArray(UserJSON, Integer.class);
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("u_id",users);
        PageHelper.startPage(pageIndex,pageSize);
        List<TUser> tUsers = tUserMapper.selectList(queryWrapper);
        PageInfo<TUser> pageInfo=new PageInfo<TUser>(tUsers);
        PageInfo<CoaUserDto> newpageInfo=new PageInfo<CoaUserDto>();
        BeanUtils.copyProperties(pageInfo,newpageInfo);
        return newpageInfo;
    }

    @Override
    public List<CoaAddressDtoList> selectCoaByAddress(double longitude,double latitude,double fanwei) {
        //放入半径和距离
        Circle circle = new Circle(new Point(longitude,latitude),fanwei*1000);
        //获得满足条件的教练名称对象
        GeoResults<RedisGeoCommands.GeoLocation<Object>> coaAddress = rt.opsForGeo().radius("userAddress", circle);
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
            List<Point> points = rt.opsForGeo().position("userAddress", Integer.parseInt(truename));
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

    //教练新增学员
    @Override
    public void insertCoaMyUser(int coaId,int userId) {
        TCoach tCoach = tCoachMapper.selectById(coaId);
        String myUser = tCoach.getMyUser();
        List<Integer> users = JSON.parseArray(myUser, Integer.class);
        users.add(userId);
        String userjsonString = JSON.toJSONString(users);
        tCoach.setMyUser(userjsonString);
        tCoachMapper.updateById(tCoach);
    }
}
