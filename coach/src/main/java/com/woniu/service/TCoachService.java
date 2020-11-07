package com.woniu.service;

import com.woniu.domain.TCoach;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-07
 */
public interface TCoachService extends IService<TCoach> {
    void insertCoach();
}
