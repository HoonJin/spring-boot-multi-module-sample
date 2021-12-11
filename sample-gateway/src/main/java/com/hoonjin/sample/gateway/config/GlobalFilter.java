package com.hoonjin.sample.gateway.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            log.info("Global Filter: {}", config.getBaseMessage());
            if (config.isPreLogger()) {
                log.info("GLOBAL PRE  Filter[" + request.getMethod() + " " + request.getPath() + "] " + request.getId());
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                ServerHttpResponse response = exchange.getResponse();
                if (config.postLogger) {
                    log.info("GLOBAL POST Filter[" + request.getMethod() + " " + request.getPath() + "] " + response.getStatusCode());
                }
            }));
        };
    }

    public GlobalFilter() {
        super(Config.class);
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
