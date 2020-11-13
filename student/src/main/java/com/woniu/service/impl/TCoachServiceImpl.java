package com.woniu.service.impl;


import com.woniu.dao.TCoachMapper;
import com.woniu.domain.TCoach;
import com.woniu.service.TCoachService;
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
public class TCoachServiceImpl extends ServiceImpl<TCoachMapper, TCoach> implements TCoachService {
    @Autowired
    private TCoachMapper tCoachMapper;
    @Override
    public List<TCoach> selectAllCoach() {
        List<TCoach> tCoaches = tCoachMapper.selectAllCoach();
        return tCoaches;
    }

    @Override
    public TCoach selectCoach(Integer coaId) {
        TCoach tCoach = tCoachMapper.selectCoach(coaId);
        return tCoach;
    }

    @Override
    public List<TCoach> searchCoaches(double price, String factions, int certificationStatus, int morningStatus, int afternoonStatus, int nightStatus) {
        return tCoachMapper.searchCoaches(price,factions,certificationStatus,morningStatus,afternoonStatus,nightStatus);
    }
}
