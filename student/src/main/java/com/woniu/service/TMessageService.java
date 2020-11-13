package com.woniu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.domain.TMessage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lx
 * @since 2020-11-12
 */
public interface TMessageService extends IService<TMessage> {
    void addUserCommit(Integer uId, String detail, Integer acceptId, String sendName);

    List<TMessage> showUserMessages(Integer uid);
}
