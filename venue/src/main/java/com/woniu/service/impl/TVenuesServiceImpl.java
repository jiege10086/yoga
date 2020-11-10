package com.woniu.service.impl;

import com.woniu.doParam.VenDoParam;
import com.woniu.domain.TVenues;
import com.woniu.mapper.TVenuesMapper;
import com.woniu.service.TVenuesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.woniu.util.DataUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zly
 * @since 2020-11-09
 */
@Service
public class TVenuesServiceImpl extends ServiceImpl<TVenuesMapper, TVenues> implements TVenuesService {
    @Resource
    private TVenuesMapper tVenuesMapper;
    @Override
    public void updateImg(VenDoParam v, MultipartFile file) throws Exception {
        //一.需要做文件格式校验
        //1.判断文件名字后缀    2.解析文件数据,文件头中的特殊文字表明了文件类型
        //二.获取文件名称 并取出后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //随机生成文件名字
        String filename = DataUtils.Time(new Date(), "yyyyMMddHHmmssSSS") + suffix;
        //直接保存到D盘的upload文件夹中
        File directory = new File("D:\\upload");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        //创建一个保存文件的File对象
        File target = new File(directory, filename);
        file.transferTo(target);
        System.out.println(filename);

        System.out.println(v.getVenId());
        TVenues tVenues = new TVenues();
        tVenues.setVenImg(filename);
        tVenues.setVenId(v.getVenId());
        tVenuesMapper.updateById(tVenues);
    }
}
