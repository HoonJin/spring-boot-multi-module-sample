package com.hoonjin.sample.gateway.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        // 우선순위를 지정하고 싶으면 OrderedGatewayFilter 를 사용
        return new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            log.info("LOGGING Filter: {}", config.getBaseMessage());
            if (config.isPreLogger()) {
                log.info("LOGGING PRE  Filter[" + request.getMethod() + " " + request.getPath() + "] " + request.getId());
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                ServerHttpResponse response = exchange.getResponse();
                if (config.postLogger) {
                    log.info("LOGGING POST Filter[" + request.getMethod() + " " + request.getPath() + "] " + response.getStatusCode());
                }
            }));
        }, Ordered.HIGHEST_PRECEDENCE);
//        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//
//            log.info("LOGGING Filter: {}", config.getBaseMessage());
//            if (config.isPreLogger()) {
//                log.info("LOGGING PRE  Filter[" + request.getMethod() + " " + request.getPath() + "] " + request.getId());
//            }
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                ServerHttpResponse response = exchange.getResponse();
//                if (config.postLogger) {
//                    log.info("LOGGING POST Filter[" + request.getMethod() + " " + request.getPath() + "] " + response.getStatusCode());
//                }
//            }));
//        };
    }

    public LoggingFilter() {
        super(Config.class);
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
