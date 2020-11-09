package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.domain.TCoach;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.mapper.TCoachMapper;
import com.woniu.param.CoaRegister;
import com.woniu.service.TCoachService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.utils.MD5Util;
import com.woniu.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-07
 */
@Service
public class TCoachServiceImpl extends ServiceImpl<TCoachMapper, TCoach> implements TCoachService {
    @Autowired
    private TCoachMapper coachMapper;

    public void selectCoachById() throws Exception{
        TCoach tCoach = coachMapper.selectById(1);
        System.out.println(tCoach);
    }

    @Override
    public TCoach CoachLogin(String name, String password) throws Exception {
        QueryWrapper<TCoach> tCoachQueryWrapper2 = new QueryWrapper<>();
        tCoachQueryWrapper2.eq("coa_name", name);
        if (coachMapper.selectOne(tCoachQueryWrapper2) == null) {
            throw new NumberNotFoundException("该用户名不存在");
        }

        tCoachQueryWrapper2.eq("coa_password", password);
        TCoach tCoach=coachMapper.selectOne(tCoachQueryWrapper2);
        if(tCoach==null){
            throw new NumberNotFoundException("密码不正确");
        }
        return tCoach;
    }

    //教练信息完善
    @Override
    public void updateCoach(TCoach tCoach) throws Exception {

        coachMapper.updateById(tCoach);
    }

    //教练注册
    @Override
    public void insertCoach(CoaRegister coaRegister) throws Exception {
        //判断电话是否重复
        if (coaRegister.getStatus() == 0) {
            QueryWrapper<TCoach> tCoachQueryWrapper = new QueryWrapper<>();
            tCoachQueryWrapper.eq("coa_phone", coaRegister.getuPhone());
            if (coachMapper.selectOne(tCoachQueryWrapper) != null) {
                throw new NumberNotFoundException("该电话已注册");
            }
            QueryWrapper<TCoach> tCoachQueryWrapper2 = new QueryWrapper<>();
            tCoachQueryWrapper2.eq("coa_name", coaRegister.getuName());
            if (coachMapper.selectOne(tCoachQueryWrapper2) != null) {
                throw new NumberNotFoundException("该用户名已注册");
            }

            TCoach tCoach = new TCoach();
            tCoach.setCoaName(coaRegister.getuName());
            tCoach.setCoaPhone(coaRegister.getuPhone());
            tCoach.setCoaPassword(MD5Util.MD5EncodeUtf8(coaRegister.getuPassword()));
            tCoach.setCoaId(UUIDUtil.getOrderNo());
            tCoach.setCoaHeadPortrait("d//img/ershou1_2.jpg");
            //动态公开状态,好友还是全部
            tCoach.setShowStatus(1);
            tCoach.setMorningStatus(0);
            tCoach.setAfternoonStatus(0);
            tCoach.setNightStatus(0);
            //认证状态
            tCoach.setCertificationStatus(0);
            //是否接受私教上课
            tCoach.setPrivateStatus(0);
            //课满状态
            tCoach.setClassFullStatus(0);
            //是否课满,不接受私教也可以搜索
            tCoach.setSearchStatus(0);
            coachMapper.insert(tCoach);
        }

        //邮箱注册
        if (coaRegister.getStatus() == 1) {
            QueryWrapper<TCoach> tCoachQueryWrapper = new QueryWrapper<>();
            tCoachQueryWrapper.eq("coa_email", coaRegister.getuEmail());
            if (coachMapper.selectOne(tCoachQueryWrapper) != null) {
                throw new NumberNotFoundException("该邮箱已注册");
            }

            QueryWrapper<TCoach> tCoachQueryWrapper2 = new QueryWrapper<>();
            tCoachQueryWrapper2.eq("coa_name", coaRegister.getuName());
            if (coachMapper.selectOne(tCoachQueryWrapper2) != null) {
                throw new NumberNotFoundException("该用户名已注册");
            }
            TCoach tCoach = new TCoach();
            tCoach.setCoaName(coaRegister.getuName());
            tCoach.setCoaEmail(coaRegister.getuEmail());
            tCoach.setCoaPassword(MD5Util.MD5EncodeUtf8(coaRegister.getuPassword()));
            tCoach.setCoaId(UUIDUtil.getOrderNo());
            tCoach.setCoaHeadPortrait("d//img/ershou1_2.jpg");
            //动态公开状态,好友还是全部
            tCoach.setShowStatus(1);
            tCoach.setMorningStatus(0);
            tCoach.setAfternoonStatus(0);
            tCoach.setNightStatus(0);
            //认证状态
            tCoach.setCertificationStatus(0);
            //是否接受私教上课
            tCoach.setPrivateStatus(0);
            //课满状态
            tCoach.setClassFullStatus(0);
            //是否课满,不接受私教也可以搜索
            tCoach.setSearchStatus(0);
            coachMapper.insert(tCoach);
        }
    }

}
