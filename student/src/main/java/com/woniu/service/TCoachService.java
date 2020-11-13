package com.woniu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.domain.TCoach;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lx
 * @since 2020-11-11
 */
public interface TCoachService extends IService<TCoach> {

    List<TCoach> selectAllCoach();

    TCoach selectCoach(Integer coaId);

    List<TCoach> searchCoaches(double price, String factions, int certificationStatus, int morningStatus, int afternoonStatus, int nightStatus);
}
