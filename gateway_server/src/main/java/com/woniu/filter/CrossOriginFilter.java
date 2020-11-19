package com.woniu.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
//@Component
public class CrossOriginFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

//		System.out.println("全局拦截器");
//		String token= exchange.getRequest().getHeaders().getFirst("token");
//		System.out.println("token:"+token);
//		if(token==null || token.isEmpty()) {
//			exchange.getResponse().setRawStatusCode(404);
//			return exchange.getResponse().setComplete();
//		}
        //RequestPath path = exchange.getRequest().getPath()
        //System.out.println(exchange.getRequest().getPath());
        //判断有无登录
        if("/coach/Tcoach/caochlogin".equals(exchange.getRequest().getPath()+"")){
            return chain.filter(exchange);
        }
        String token = exchange.getRequest().getHeaders().getFirst("X-Token");
        if(token==null||token.trim().isEmpty()) {
            //未登录
            exchange.getResponse().setRawStatusCode(403);
            return exchange.getResponse().setComplete();
        }


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
