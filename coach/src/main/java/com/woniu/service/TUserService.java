package com.woniu.service;

import com.github.pagehelper.PageInfo;
import com.woniu.domain.TUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.dto.CoaAddressDtoList;
import com.woniu.dto.CoaUserDto;
import com.woniu.dto.CoachDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-07
 */
public interface TUserService extends IService<TUser> {
    PageInfo<CoaUserDto> selectUserByCoa(int coaId, int pageSize, int pageIndex);

    List<CoaAddressDtoList> selectCoaByAddress(double longitude,double latitude,double fanwei);

    void insertCoaMyUser (int coaId,int userId);

}
