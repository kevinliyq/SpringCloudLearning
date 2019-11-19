package com.forezp.servicegateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class BlacklistUserFilter implements GatewayFilter, Ordered
{
    private Logger logger = LoggerFactory.getLogger(BlacklistUserFilter.class);

    @Override public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {

        String user = String.valueOf(exchange.getRequest().getQueryParams().getFirst("name"));

        if ("blacklist".equals(user))
        {
            return Mono.error(new IllegalArgumentException("Invalid user"));
        }

        return chain.filter(exchange);
    }

    @Override public int getOrder()
    {
        return 0;
    }
}
