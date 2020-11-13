package com.woniu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.domain.TVenues;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lx
 * @since 2020-11-11
 */
public interface TVenuesMapper extends BaseMapper<TVenues> {

    List<TVenues> selectAllVenues();

    TVenues selectVenue(Integer venId);
}
