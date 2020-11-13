package com.woniu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.domain.TVenues;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lx
 * @since 2020-11-11
 */
public interface TVenuesService extends IService<TVenues> {

    List<TVenues> selectAllVenues();

    TVenues selectVenue(Integer venId);
}
