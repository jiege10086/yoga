package com.woniu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.domain.TCoach;
import com.woniu.domain.TConsumption;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.mapper.TCoachMapper;
import com.woniu.mapper.TConsumptionMapper;
import com.woniu.param.CoaRegister;
import com.woniu.service.TCoachService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.utils.MD5Util;
import com.woniu.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private TConsumptionMapper tConsumptionMapper;

    @Autowired
    private RedisTemplate<String, Object> rt;

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

    //新密码返回
    @Override
    public String newPassword(String coaName,Integer status) throws Exception {
        if(status==0){
            //发送给电话
        }
        if(status==1){
            //发送给邮箱
        }
        String password = UUID.randomUUID().toString().substring(0, 6);
        rt.opsForValue().set("newpassword"+coaName,password,30*60, TimeUnit.SECONDS);
        return password;
    }

    //新密码登录
    @Override
    public void newPasswordLogin(String coaName,String password) throws Exception {
        Object newpassword = rt.opsForValue().get("newpassword" + coaName);
        if(newpassword==null){
            throw new NumberNotFoundException("密码已过期");
        }
        if(!(password.equals(newpassword.toString()))){
            throw new NumberNotFoundException("密码不正确");
        }
        QueryWrapper<TCoach> tCoachQueryWrapper = new QueryWrapper<>();
        tCoachQueryWrapper.eq("coa_name", coaName);
        TCoach coach = coachMapper.selectOne(tCoachQueryWrapper);
        coach.setCoaPassword(MD5Util.MD5EncodeUtf8(newpassword.toString()));
        coachMapper.updateById(coach);
    }

    //取现
    public void coaGetMoney(Integer bankcard, Double money, Integer coaId) throws Exception {
        TCoach coach = coachMapper.selectById(coaId);
        if(coach==null){
            throw new NumberNotFoundException("该用户不存在");
        }
        if(coach.getCoaMoney()<money){
            throw new NumberNotFoundException("您的余额不足");
        }
        coach.setCoaMoney(coach.getCoaMoney()-money);
        TConsumption tConsumption = new TConsumption();
        tConsumption.setDetail("取现");
        //0是取现,1是消费
        tConsumption.setStatus(1);
        tConsumption.setMoney(money);
        tConsumption.setPeopleId(coaId);
        tConsumption.setPeoRole(1);
        tConsumptionMapper.insert(tConsumption);
    }

    //教练添加关注
    @Override
    public void insertAttention(Integer coaId, Integer peoId, Integer role) {
        TCoach tCoach = coachMapper.selectById(coaId);
        rt.opsForHash().put("Attention:"+coaId,peoId+"",111);
        if(role==0){
            String user = tCoach.getAttentionUser();
            List<Integer> userarray = JSON.parseArray(user,Integer.class);
            if (userarray==null){
                userarray=new ArrayList<Integer>();
            }
            userarray.add(peoId);
            String jsonString = JSON.toJSONString(userarray);
            tCoach.setAttentionUser(jsonString);
        }
        if(role==1){
            String user = tCoach.getAttentionCoach();
            List<Integer> userarray = JSON.parseArray(user,Integer.class);
            if (userarray==null){
                userarray=new ArrayList<Integer>();
            }
            userarray.add(peoId);
            String jsonString = JSON.toJSONString(userarray);
            tCoach.setAttentionCoach(jsonString);
        }
        if(role==2){
            String user = tCoach.getAttentionVenues();
            List<Integer> userarray = JSON.parseArray(user,Integer.class);
            if (userarray==null){
                userarray=new ArrayList<Integer>();
            }
            userarray.add(peoId);
            String jsonString = JSON.toJSONString(userarray);
            tCoach.setAttentionVenues(jsonString);
        }
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
