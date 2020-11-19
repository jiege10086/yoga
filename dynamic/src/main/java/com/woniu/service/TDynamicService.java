package com.woniu.service;

import com.github.pagehelper.PageInfo;
import com.woniu.domain.TDynamic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.dto.DynamicDto;
import com.woniu.param.DynamicParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-11
 */
public interface TDynamicService extends IService<TDynamic> {
    PageInfo<DynamicDto> selectDynById(int id,int pageSize,int pageIndex);

    PageInfo<DynamicDto> selectDynMyId(int uuid, int pageSize, int pageIndex,int role);

    void insertDynByUuid(DynamicParam dynamicParam);

    boolean friend(int selectId,int accpetId);

    //主页图文
    PageInfo<DynamicDto> selectDynByAll(int pageSize,int pageIndex);
}
