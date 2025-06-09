package org.grandmasfood.springcloud.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseCookie;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class GatewayGlobalFilters implements GlobalFilter, Ordered {
    private final Logger log = LoggerFactory.getLogger(GatewayGlobalFilters.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Info request");

        exchange.getRequest().mutate().headers(h -> h.add("token", "oaofkasok"));

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("Info response");
            String token = exchange.getRequest().getHeaders().getFirst("token");
            if (token != null) {
                log.info("token: " + token);
            }

            Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(value -> {
                log.info("token: " + token);
                exchange.getResponse().getHeaders().add("token", value);
            });

            log.info("token: " + token);
            exchange.getRequest().getCookies().add("color", ResponseCookie.from("color", "red").build());
//            exchange.getRequest().getHeaders().setContentType(MediaType.TEXT_PLAIN);

        }));
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
