package com.woniu.service.impl;

import com.woniu.domain.TCoach;
import com.woniu.mapper.TCoachMapper;
import com.woniu.service.TCoachService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-07
 */
@Service
public class TCoachServiceImpl extends ServiceImpl<TCoachMapper, TCoach> implements TCoachService {
    @Autowired
    private TCoachMapper coachMapper;

    public void selectCoachById(){
        TCoach tCoach = coachMapper.selectById(1);
        System.out.println(tCoach);
    }

    @Override
    public void insertCoach() {

    }
}
