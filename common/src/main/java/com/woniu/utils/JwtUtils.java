package com.woniu.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;
import java.util.Date;


public class JwtUtils {
    static String key="jiejiangfsafdsavswfewvavsdagsavsvs";


    //生成
    public static String createToken(Object obj) throws Throwable, IllegalAccessException {
        Map<String, Object> token = new HashMap<String, Object>();
        Class<? extends Object> oclass = obj.getClass();
        Field[] fields = oclass.getDeclaredFields();
        for(Field f:fields) {
            f.setAccessible(true);
            token.put(f.getName(), f.get(obj));
        }


        Key keyFor = Keys.hmacShaKeyFor(key.getBytes());
        return Jwts.builder().setClaims(token)
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 1000))
                .signWith(keyFor)
                .compact();
    }

    //解析rin
    public static <T> T parseToken(String token,Class<T> cla) throws Throwable {
        Key keyFor = Keys.hmacShaKeyFor(key.getBytes());
        //String token=createToken();
        //System.out.println(token);
        //System.out.println(Jwts.parserBuilder().setSigningKey(keyFor).build().parseClaimsJws(token).getBody());
        //token=token.substring(1,token.length()-1);

        Claims body = Jwts.parserBuilder().setSigningKey(keyFor).build().parseClaimsJws(token).getBody();

        Object instance = cla.newInstance();

        Field[] fields = cla.getDeclaredFields();
        for(Field f:fields) {
            f.setAccessible(true);
            f.set(instance, body.get(f.getName()));
        }
        return (T) instance;
    }
}
