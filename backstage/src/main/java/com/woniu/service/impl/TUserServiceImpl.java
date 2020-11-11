package com.woniu.service.impl;

import com.woniu.domain.TUser;
import com.woniu.mapper.TUserMapper;
import com.woniu.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zly
 * @since 2020-11-11
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

}
