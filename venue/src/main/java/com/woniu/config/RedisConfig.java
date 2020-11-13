package com.woniu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.Charset;

@Configuration
public class RedisConfig {
    @Bean("rt")
    public RedisTemplate<String, Object> createRedis(RedisConnectionFactory rcf) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(rcf);
        StringRedisSerializer srs = new StringRedisSerializer(Charset.forName("utf-8"));
        Jackson2JsonRedisSerializer<Object> jsd = new Jackson2JsonRedisSerializer<>(Object.class);
        template.setKeySerializer(srs);
        template.setValueSerializer(jsd);
        template.setHashKeySerializer(srs);
        template.setHashValueSerializer(jsd);
        return  template;
    }
}
