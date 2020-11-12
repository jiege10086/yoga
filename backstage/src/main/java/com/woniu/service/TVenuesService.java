package com.woniu.service;

import com.woniu.domain.TVenues;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.doparam.VenDoParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zly
 * @since 2020-11-11
 */
public interface TVenuesService extends IService<TVenues> {
    void insertImg(TVenues v, MultipartFile file)throws Exception;
}
