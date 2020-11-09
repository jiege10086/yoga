package com.woniu.service;

import com.woniu.doParam.VenDoParam;
import com.woniu.domain.TVenues;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zly
 * @since 2020-11-09
 */
public interface TVenuesService extends IService<TVenues> {
    void updateImg(VenDoParam v, MultipartFile file)throws Exception;
}
