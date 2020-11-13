package com.woniu.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.domain.TMessage;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lx
 * @since 2020-11-12
 */
public interface TMessageMapper extends BaseMapper<TMessage> {

    void addUserCommit(Integer uId, String detail, Integer acceptId, String sendName);

    List<TMessage> showUserMessages(Integer uid);
}
