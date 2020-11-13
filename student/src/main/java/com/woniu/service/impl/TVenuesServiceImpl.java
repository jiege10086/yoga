package com.woniu.service.impl;


import com.woniu.dao.TVenuesMapper;
import com.woniu.domain.TVenues;
import com.woniu.service.TVenuesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lx
 * @since 2020-11-11
 */
@Service
public class TVenuesServiceImpl extends ServiceImpl<TVenuesMapper, TVenues> implements TVenuesService {
    @Autowired
    private TVenuesMapper tVenuesMapper;
    @Override
    public List<TVenues> selectAllVenues() {
        List<TVenues> tVenues = tVenuesMapper.selectAllVenues();
        return tVenues;
    }

    @Override
    public TVenues selectVenue(Integer venId) {
        TVenues tVenues = tVenuesMapper.selectVenue(venId);
        return tVenues;
    }
}
