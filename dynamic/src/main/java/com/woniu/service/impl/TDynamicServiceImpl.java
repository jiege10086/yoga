package com.woniu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.domain.TCoach;
import com.woniu.domain.TDynamic;
import com.woniu.domain.TUser;
import com.woniu.dto.DynamicDto;
import com.woniu.mapper.TCoachMapper;
import com.woniu.mapper.TDynamicMapper;
import com.woniu.mapper.TUserMapper;
import com.woniu.param.DynamicParam;
import com.woniu.service.TDynamicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2020-11-11
 */
@Service
public class TDynamicServiceImpl extends ServiceImpl<TDynamicMapper, TDynamic> implements TDynamicService {
    @Resource
    private TDynamicMapper tDynamicMapper;
    @Resource
    private TCoachMapper tCoachMapper;
    @Resource
    private TUserMapper tUserMapper;
    @Autowired
    private RedisTemplate<String, Object> rt;
    //根据id查询动态
    @Override
    public PageInfo<DynamicDto> selectDynById(int id, int pageSize, int pageIndex) {
        QueryWrapper<TDynamic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid",id);
        PageHelper.startPage(pageIndex,pageSize);
        List<TDynamic> tDynamics = tDynamicMapper.selectList(queryWrapper);
        PageInfo<TDynamic> tDynamicPageInfo = new PageInfo<>(tDynamics);
        PageInfo<DynamicDto> DynamicDtoPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(tDynamicPageInfo,DynamicDtoPageInfo);
        return DynamicDtoPageInfo;
    }

    //根据自己的id查询我的关注接口
    @Override
    public PageInfo<DynamicDto> selectDynMyId(int uuid, int pageSize, int pageIndex,int role) {
        ArrayList<Object> list = new ArrayList<>();
        if(role==0){
            TUser tUser = tUserMapper.selectById(uuid);
            String attentionCoach = tUser.getuAttentionCoach();
            String attentionUser = tUser.getuAttentionUser();
            String attentionVenues = tUser.getuAttentionVenues();
            JSONArray coachs = JSON.parseArray(attentionCoach);
            JSONArray users = JSON.parseArray(attentionUser);
            JSONArray venues = JSON.parseArray(attentionVenues);
            if(coachs!=null){
                list.addAll(coachs);
            }
            if(users!=null){
                list.addAll(users);
            }
            if(venues!=null){
                list.addAll(venues);
            }
        }
        if(role==1){
            TCoach tCoach = tCoachMapper.selectById(uuid);
            String attentionCoach = tCoach.getAttentionCoach();
            String attentionUser = tCoach.getAttentionUser();
            String attentionVenues = tCoach.getAttentionVenues();
            JSONArray coachs = JSON.parseArray(attentionCoach);
            JSONArray users = JSON.parseArray(attentionUser);
            JSONArray venues = JSON.parseArray(attentionVenues);
            if(coachs!=null){
                list.addAll(coachs);
            }
            if(users!=null){
                list.addAll(users);
            }
            if(venues!=null){
                list.addAll(venues);
            }
        }

        QueryWrapper<TDynamic> queryWrapper = new QueryWrapper<>();
        PageHelper.startPage(pageIndex,pageSize);
        queryWrapper.in("uuid",list);
        List<TDynamic> tDynamics = tDynamicMapper.selectList(queryWrapper);
        PageInfo<TDynamic> tDynamicPageInfo = new PageInfo<>(tDynamics);
        PageInfo<DynamicDto> DynamicDtoPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(tDynamicPageInfo,DynamicDtoPageInfo);
        return DynamicDtoPageInfo;
    }

    //新增一条动态信息
    @Override
    public void insertDynByUuid(DynamicParam dynamicParam) {
        TDynamic tDynamic = new TDynamic();
        tDynamic.setOpenDetail(dynamicParam.getOpenDetail());
        tDynamic.setImgs(JSON.toJSONString(dynamicParam.getImgs()));
        tDynamic.setPeoName(dynamicParam.getPeoName());
        tDynamic.setPeoRole(dynamicParam.getPeoRole());
        tDynamic.setUuid(dynamicParam.getUuid());
        System.out.println(dynamicParam);
        System.out.println(tDynamic);
        tDynamicMapper.insert(tDynamic);
    }

    //判断是否好有接口
    @Override
    public boolean friend(int selectId, int accpetId) {
        //判断是否是好友
        if(rt.opsForHash().get("Attention"+selectId,accpetId+"")!=null&&
        rt.opsForHash().get("Attention"+accpetId,selectId+"")!=null){
            return true;
        }
        return false;
    }

    //主页动态
    @Override
    public PageInfo<DynamicDto> selectDynByAll(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex,pageSize);
        QueryWrapper<TDynamic> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("dyn_id");
        List<TDynamic> tDynamics = tDynamicMapper.selectList(queryWrapper);
        PageInfo<TDynamic> tDynamicPageInfo = new PageInfo<>(tDynamics);
        PageInfo<DynamicDto> DynamicDtoPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(tDynamicPageInfo,DynamicDtoPageInfo);
        return DynamicDtoPageInfo;
    }


}
