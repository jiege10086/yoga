package com.woniu.util;

import org.apache.commons.mail.HtmlEmail;

import java.util.Random;

public class EmailUtil {
    public static String sendCode(String emailAddress) throws Exception{
        HtmlEmail email=new HtmlEmail();//创建一个HtmlEmail实例对象
        email.setHostName("smtp.163.com");//邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setCharset("utf-8");//设置发送的字符类型
        email.addTo(emailAddress);//设置收件人
        email.setFrom("m15023652381@163.com","yujia");//发送人的邮箱为自己的，用户名可以随便填
        email.setAuthentication("m15023652381@163.com","KAZZJLSXMORGJMBO");//设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
        email.setSubject("这是一条由瑜伽App验证消息");//设置发送主题
        String mess =newVerifyCode();
        email.setMsg(mess);//设置发送内容
        email.send();//进行发送


        return mess;

    }
    public static String newVerifyCode() {
        Random random = new Random();
        String str = "";
        for (int i = 0; i < 6; i++) {
            int key = random.nextInt(3);
            switch (key) {
                case 0:
                    int code1 = random.nextInt(10);
                    str += code1;
                    break;
                case 1:
                    char code2 = (char) (random.nextInt(26) + 65);
                    str += code2;
                    break;
                case 2:
                    char code3 = (char) (random.nextInt(26) + 97);
                    str += code3;
                    break;
            }
        }
        return str;
    }


}
