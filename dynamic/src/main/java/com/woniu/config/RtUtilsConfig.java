package com.woniu.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.Charset;

/** 
* @author 作者:jiejiang E-mail:1289687985@qq.com: 
* @version 创建时间：2020年10月28日 上午11:53:39 
* 方法说明 :
*/
@Configuration
public class RtUtilsConfig {
	
	@Bean("rt")
	public RedisTemplate<String,Object> createRt(RedisConnectionFactory crf){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(crf);
		StringRedisSerializer key = new StringRedisSerializer(Charset.forName("utf-8"));
		redisTemplate.setKeySerializer(key);
		Jackson2JsonRedisSerializer<Object> value = new Jackson2JsonRedisSerializer<>(Object.class);
		redisTemplate.setValueSerializer(value);
		redisTemplate.setHashKeySerializer(key);
		redisTemplate.setHashValueSerializer(value);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
		return interceptor;
	}
}
