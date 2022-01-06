package com.hoonjin.sample.gateway.filter;

import io.jsonwebtoken.Jwts;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final Environment environment;

    public AuthorizationHeaderFilter(Environment environment) {
        super(Config.class);
        this.environment = environment;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                String authorization = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                String token = authorization.replace("Bearer ", "");

                if (isValidJwt(token)) {
                    return chain.filter(exchange);
                } else {
                    return onError(exchange);
                }
            } else {
                return onError(exchange);
            }
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        log.error("");
        return response.setComplete();
    }

    private boolean isValidJwt(String token) {
        try {
//            String subject = Jwts.parserBuilder()
//                    .setSigningKey(environment.getProperty("token.secret"))
//                    .build()
//                    .parseClaimsJws(token).getBody()
//                    .getSubject();
            String subject = Jwts.parser()
                    .setSigningKey(environment.getProperty("token.secret"))
                    .parseClaimsJws(token).getBody()
                    .getSubject();
            return !(subject == null || subject.isEmpty());
        } catch (Exception e) {
            return false;
        }
    }

    @Data
    public static class Config {

    }
}
