package com.woniu.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.doParam.VenDoParam;
import com.woniu.domain.TCoach;
import com.woniu.domain.TVenues;
import com.woniu.dot.VenCoach;
import com.woniu.dot.VenDotCoachs;
import com.woniu.dot.VenDotToken;
import com.woniu.service.TCoachService;
import com.woniu.service.TVenuesService;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import com.woniu.utils.MD5Util;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
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

    /*
     * 登录
     * */
    @GetMapping("/login")
    public JSONResult select(HttpServletResponse response, VenDoParam ven) throws Throwable {
        QueryWrapper<TVenues> tVenuesQueryWrapper = new QueryWrapper<>();
        tVenuesQueryWrapper.eq("ven_phone", ven.getVenPhone()).eq("ven_password", MD5Util.MD5EncodeUtf8(ven.getVenPassword()));
        TVenues vens = tVenuesService.getOne(tVenuesQueryWrapper);
        VenDotToken build = VenDotToken.builder().venId(vens.getVenId() + "").role(2 + "").build();
        String token = JwtUtils.createToken(build);

        response.setHeader("X-token", token);
        System.out.println(token);
        return new JSONResult("200", "success", null, vens);
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
            VenCoach venCoach = JSON.parseObject(byId.getVenCoachs(), VenCoach.class);
            List<Object> list = new ArrayList<>();
            for (int i = 0; i < venCoach.getCoachs().size(); i++) {
                Integer id = venCoach.getCoachs().get(i).getId();
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
        VenCoach venCoach = JSON.parseObject(byId.getVenCoachs(), VenCoach.class);
        System.out.println(coaId);
        for (int i = 0; i < venCoach.getCoachs().size(); i++) {
            if(venCoach.getCoachs().get(i).getId()==coaId){
                venCoach.getCoachs().remove(i);
                break;
            }
        }
        String s = JSON.toJSON(venCoach).toString();
        TVenues build1 = TVenues.builder().venCoachs(s).build();
        UpdateWrapper<TVenues> tup = new UpdateWrapper<>();
        tup.eq("ven_id",build.getVenId()).set("ven_coachs",build1.getVenCoachs());
        boolean update = tVenuesService.update(tup);
        return new JSONResult("200","success",null,null);
    }
}

