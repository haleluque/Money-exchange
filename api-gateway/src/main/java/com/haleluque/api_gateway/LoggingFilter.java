package com.haleluque.api_gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {
    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    /**
     * Method to log any incoming request to the gateway
     *
     * @param exchange information to be logged
     * @param chain allows the continuation of the API request after logging
     * @return void
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
        logger.info("Path of the request received -> {}",
                exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
