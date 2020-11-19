package com.woniu.service;

import com.github.pagehelper.PageInfo;
import com.woniu.domain.TVenues;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.dto.VenusDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-19
 */
public interface TVenuesService extends IService<TVenues> {
    //查询教练,倒叙
    PageInfo<VenusDto> selectVenues(String name, int pageSize, int pageIndex);

    //查询单个教练
    VenusDto selectVenues(int venId);
}
