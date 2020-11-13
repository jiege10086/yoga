package com.woniu.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.domain.TCoach;
import com.woniu.domain.TSigning;
import com.woniu.domain.TVenues;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.mapper.TCoachMapper;
import com.woniu.mapper.TSigningMapper;
import com.woniu.mapper.TVenuesMapper;
import com.woniu.service.TSigningService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-12
 */
@Service
public class TSigningServiceImpl extends ServiceImpl<TSigningMapper, TSigning> implements TSigningService {
    @Resource
    private TSigningMapper tSigningMapper;
    @Resource
    private TVenuesMapper tVenuesMapper;
    @Resource
    private TCoachMapper tCoachMapper;

    //教练添加对场馆的签约请求
    @Override
    public void insertSigningByCoa(int coaId,int venId,String detail) {
        TSigning tSigning = new TSigning();
        tSigning.setSendId(coaId);
        tSigning.setAcceptId(venId);
        tSigning.setDetail(detail);
        tSigning.setAgreeStatus(0);
        tSigning.setSendStatus(0);
        tSigningMapper.insert(tSigning);
    }

    //教练查询场馆对自己签约信息
    @Override
    public PageInfo<TSigning> selectSigningByCoa(int coaId,int agreeStatus,int status,int pageSize,int pageIndex) {
        QueryWrapper<TSigning> queryWrapper = new QueryWrapper<>();
        if(agreeStatus!=-1){
            queryWrapper.eq("agree_status",agreeStatus);
        }
        if(status==0){
            queryWrapper.eq("send_id",coaId);
        }
        if (status==1){
            queryWrapper.eq("accert_id",coaId);
        }
        PageHelper.startPage(pageIndex,pageSize);
        List<TSigning> tSignings = tSigningMapper.selectList(queryWrapper);
        PageInfo<TSigning> signingPageInfo = new PageInfo<>(tSignings);
        return signingPageInfo;
    }

    //教练是否同意签约
    @Override
    public void updateSigningByCoa(int coaId,int sigId,int venId,int agreeStatus) throws NumberNotFoundException {

        if(agreeStatus==1){
            //修改认证状态
            TCoach tCoach = tCoachMapper.selectById(coaId);
            tCoach.setCertificationStatus(1);
            tCoachMapper.updateById(tCoach);
            //修改场馆的认证教练
            TVenues tVenues = tVenuesMapper.selectById(venId);
            String coachs = tVenues.getVenCoachs();
            List<Integer> caochs = JSON.parseArray(coachs, Integer.class);
            for(Integer caoch:caochs){
                if (caoch==coaId){
                    throw new NumberNotFoundException("您已认证过了");
                }
            }
            caochs.add(coaId);
            String s = JSON.toJSONString(caochs);
            tVenues.setVenCoachs(s);
            tVenuesMapper.updateById(tVenues);
        }
        TSigning tSigning = new TSigning();
        tSigning.setSigId(sigId);
        tSigning.setAgreeStatus(agreeStatus);
        tSigningMapper.updateById(tSigning);
    }

}
