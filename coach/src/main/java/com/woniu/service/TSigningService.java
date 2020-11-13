package com.woniu.service;

import com.github.pagehelper.PageInfo;
import com.woniu.domain.TSigning;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.exception.NumberNotFoundException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-12
 */
public interface TSigningService extends IService<TSigning> {
    void insertSigningByCoa(int coaId,int venId,String detail);

    PageInfo<TSigning> selectSigningByCoa(int coaId, int agreeStatus, int status, int pageSize, int pageIndex);

    void updateSigningByCoa(int coaId,int sigId,int venId,int agreeStatus) throws NumberNotFoundException;
}
