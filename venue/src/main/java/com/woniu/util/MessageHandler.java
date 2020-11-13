package com.woniu.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Map;

/**
 * @version: V1.0
 * @author: Xbao
 * @className: AliyunMessage
 * @packageName: com.woniu.project.common.core.util
 * @description: 阿里云短信
 * @data: 2020-10-13 16:13
 */
public class MessageHandler {
    public boolean send(String phone, String templateCode, Map<String,Object> code){
        //  连接阿里云  第二个参数是密钥id 第三个参数是密码
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G5wdaKYAGVseJXYpRZh", "cp0LC9rJajtoq5S5oZtZg62kNYPmdn");
        IAcsClient client = new DefaultAcsClient(profile);
        //构件请求
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");//定死不修改
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        //自定义的参数（手机号，签名，模板,验证码！）
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "ABC商城");
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(code));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }
}
