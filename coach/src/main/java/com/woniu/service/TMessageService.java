package com.woniu.service;

import com.github.pagehelper.PageInfo;
import com.woniu.domain.TMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.dto.CoaDtoToken;
import com.woniu.dto.CoaMessageDto;
import com.woniu.param.CoaMessageParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-10
 */
public interface TMessageService extends IService<TMessage> {

    //教练查看自己所有的留言
    PageInfo<CoaMessageDto> selectMessageByCoaId(int coaId, int pageSize, int pageIndex, String status);

    //教练新增一条留言
    void insertMessage(CoaMessageParam coaMessageParam, CoaDtoToken coaDtoToken);
}
