package com.woniu.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.doParam.VenDoParam;
import com.woniu.domain.TCoach;
import com.woniu.domain.TSigning;
import com.woniu.domain.TVenues;
import com.woniu.dot.VenCoach;
import com.woniu.dot.VenDotCoachs;
import com.woniu.dot.VenDotToken;
import com.woniu.service.TCoachService;
import com.woniu.service.TSigningService;
import com.woniu.service.TVenuesService;
import com.woniu.util.EmailUtil;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import com.woniu.utils.MD5Util;
import com.woniu.utils.UUIDUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zly
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/tVenues")
public class TVenuesController {

    @Resource
    private TVenuesService tVenuesService;

    @Resource
    private TCoachService tCoachService;

    @Resource
    private TSigningService tSigningService;
    /*
     * 登录
     * */
    @GetMapping("/login")
    public JSONResult select(HttpServletResponse response, VenDoParam ven) throws Throwable {

        QueryWrapper<TVenues> tVenuesQueryWrapper = new QueryWrapper<>();
        if(ven.getVenPhone()!=null){
            tVenuesQueryWrapper.eq("ven_phone", ven.getVenPhone());
        }
        if(!StringUtils.isEmpty(ven.getVenEmail())){
            tVenuesQueryWrapper.eq("ven_email",ven.getVenEmail());
        }
        if(!StringUtils.isEmpty(ven.getVenPassword())){
            tVenuesQueryWrapper.eq("ven_password", MD5Util.MD5EncodeUtf8(ven.getVenPassword()));
        }
        TVenues vens = tVenuesService.getOne(tVenuesQueryWrapper);
        VenDotToken build = VenDotToken.builder().venId(vens.getVenId() + "").role(2 + "").build();
        String token = JwtUtils.createToken(build);

        response.setHeader("X-token", token);
        System.out.println(token);
        return new JSONResult("200", "success", null, vens);
    }
    /*
    * 邮箱发送密码
    * */
    @RequestMapping("/email")
    public JSONResult emailPassword(VenDoParam ven)throws Exception{
        EmailUtil.sendCode(ven.getVenEmail());
        return new JSONResult("200","success",null,null);
    }
    /*
    * 邮箱注册
    * */
    @RequestMapping("/emailReg")
    public JSONResult emailRegisster(VenDoParam ven)throws Exception{
        TVenues build = TVenues.builder().venId(UUIDUtil.getOrderNo()).venEmail(ven.getVenEmail()).venPassword(MD5Util.MD5EncodeUtf8(ven.getVenPassword())).build();
        boolean save = tVenuesService.save(build);
        return new JSONResult("200","success",null,null);
    }
    /*
     * 场馆信息完善
     * */
    @RequestMapping("/updateVenue")
    public JSONResult update(VenDoParam v, MultipartFile file) throws Exception {
        UpdateWrapper<TVenues> tVenuesUpdateWrapper = new UpdateWrapper<>();
        tVenuesUpdateWrapper.eq("ven_id", v.getVenId()).set("ven_address", v.getVenAddress()).set("ven_name", v.getVenName()).set("ven_detail", v.getVenDetail()).set("ven_bulk", v.getVenBulk());
        boolean update = tVenuesService.update(tVenuesUpdateWrapper);

        tVenuesService.updateImg(v, file);
        return new JSONResult("200", "success", null, null);
    }

    /*
     * 查看我的签约教练
     * */
    @GetMapping("/selectCoachs")
    public JSONResult selectAll(@RequestHeader("X-token") String token) throws Throwable {

        VenDotToken venDotToken = JwtUtils.parseToken(token, VenDotToken.class);
        TVenues build = TVenues.builder().venId(Integer.parseInt(venDotToken.getVenId())).build();
        TVenues byId = tVenuesService.getById(build);
        if (byId != null) {
            List<Integer> integers = JSON.parseArray(byId.getVenCoachs(), Integer.class);
            List<Object> list = new ArrayList<>();
            for (int i = 0; i < integers.size(); i++) {
                Integer id = integers.get(i);
                QueryWrapper<TCoach> tc = new QueryWrapper<>();
                tc.eq("coa_id", id);
                list.add(tCoachService.list(tc));

            }
            return new JSONResult("200", "success", list, null);
        }
        return new JSONResult("500", "没有签约教练", null, null);
    }

    /*
    * 解约教练
    * */
    @RequestMapping("/removeCoach")
    public JSONResult removeCoach(@RequestHeader("X-token") String token,Integer coaId) throws Throwable {
        VenDotToken venDotToken = JwtUtils.parseToken(token, VenDotToken.class);
        TVenues build = TVenues.builder().venId(Integer.parseInt(venDotToken.getVenId())).build();
        TVenues byId = tVenuesService.getById(build);
        if(byId==null){
            return new JSONResult("500", "没有该教练", null, null);
        }
        List<Integer> integers = JSON.parseArray(byId.getVenCoachs(), Integer.class);
        for (int i = 0; i < integers.size(); i++) {
            if(integers.get(i)==coaId){
                integers.remove(i);
                break;
            }
        }
        String s = JSON.toJSON(integers).toString();
        UpdateWrapper<TVenues> tup = new UpdateWrapper<>();
        tup.eq("ven_id",build.getVenId()).set("ven_coachs",s);
        boolean update = tVenuesService.update(tup);
        return new JSONResult("200","success",null,null);
    }

    /*
    * 同意教练签约场馆
    * */
    @RequestMapping("/updateSig")
    public JSONResult updateSig(@RequestHeader("X-token") String token,Integer coaId) throws Throwable {
        if(token==null){
            return new JSONResult("500","权限不足",null,null);
        }
        VenDotToken venDotToken = JwtUtils.parseToken(token, VenDotToken.class);
        TVenues build = TVenues.builder().venId(Integer.parseInt(venDotToken.getVenId())).build();
        UpdateWrapper<TSigning> tSigningUpdateWrapper = new UpdateWrapper<>();
        tSigningUpdateWrapper.eq("send_id",coaId).eq("accept_id",build.getVenId()).set("agree_status",1);
        boolean update = tSigningService.update(tSigningUpdateWrapper);
        if(update){
            TCoach byId = tCoachService.getById(coaId);
            System.out.println(byId.getBelongsVenues());
            if(byId.getBelongsVenues()==null||byId.getBelongsVenues().equals("")){
                ArrayList<Integer> list = new ArrayList<>();
                list.add(build.getVenId());
                String s = JSON.toJSON(list).toString();
                System.out.println(s);
                UpdateWrapper<TCoach> coachUpdateWrapper = new UpdateWrapper<>();
                coachUpdateWrapper.eq("coa_id",coaId).set("belongs_venues",s);
                tCoachService.update(coachUpdateWrapper);
            }else {
            String belongsVenues = byId.getBelongsVenues();
            List<Integer> integers = JSON.parseArray(belongsVenues, Integer.class);
            integers.add(build.getVenId());
            String s = JSON.toJSON(integers).toString();
            UpdateWrapper<TCoach> coachUpdateWrapper = new UpdateWrapper<>();
            coachUpdateWrapper.eq("coa_id",coaId).set("belongs_venvues",s);
            tCoachService.update(coachUpdateWrapper);
            }
        }
        return new JSONResult("200","success",null,null);
    }

}

