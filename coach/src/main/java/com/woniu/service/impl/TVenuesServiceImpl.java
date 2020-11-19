package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.domain.TVenues;
import com.woniu.dto.VenusDto;
import com.woniu.fen.DynamicFen;
import com.woniu.mapper.TVenuesMapper;
import com.woniu.service.TVenuesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-19
 */
@Service
public class TVenuesServiceImpl extends ServiceImpl<TVenuesMapper, TVenues> implements TVenuesService {
    @Resource
    private TVenuesMapper tVenuesMapper;



    //主页查询教练
    @Override
    public PageInfo<VenusDto> selectVenues(String name, int pageSize, int pageIndex) {
        QueryWrapper<TVenues> queryWrapper = new QueryWrapper<>();
        if (name!=null&&name!=""){
            queryWrapper.eq("ven_name",name);
        }
        queryWrapper.eq("ven_status",1);
        PageHelper.startPage(pageIndex,pageSize);
        List<TVenues> tVenues = tVenuesMapper.selectList(queryWrapper);
        PageInfo<TVenues> pageInfo = new PageInfo<TVenues>(tVenues);
        PageInfo<VenusDto> venuser = new PageInfo<VenusDto>();
        BeanUtils.copyProperties(pageInfo,venuser);
        return venuser;
    }

    //教练查询单个场馆
    @Override
    public VenusDto selectVenues(int venId) {
        QueryWrapper<TVenues> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ven_status",1);
        queryWrapper.eq("ven_id",venId);
        TVenues tVenues = tVenuesMapper.selectOne(queryWrapper);
        VenusDto venusDto = new VenusDto();
        BeanUtils.copyProperties(tVenues,venusDto);
        return venusDto;
    }
}
