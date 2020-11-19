package com.woniu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.woniu.domain.TMessage;
import com.woniu.dto.CoaDtoToken;
import com.woniu.dto.CoaMessageDto;
import com.woniu.dto.MessageDto;
import com.woniu.param.CoaMessageParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lx
 * @since 2020-11-16
 */
public interface TMessageService extends IService<TMessage> {
    void addUserCommit(Integer uId, String detail, String acceptId, String sendName);

    PageInfo<CoaMessageDto> selectMessageByCoaId(int parseInt, int pageSize, int pageIndex, String status);

    void insertMessage(CoaMessageParam coaMessageParam, CoaDtoToken coach);

    PageInfo<MessageDto> showUserMessages(int uId, int pageIndex, int pageSize);
}
