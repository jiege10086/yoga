package com.woniu.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.domain.TCoach;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lx
 * @since 2020-11-11
 */
public interface TCoachMapper extends BaseMapper<TCoach> {

    List<TCoach> selectAllCoach();

    TCoach selectCoach(Integer coaId);

    List<TCoach> searchCoaches(double price, String factions, int certificationStatus, int morningStatus, int afternoonStatus, int nightStatus);
}
