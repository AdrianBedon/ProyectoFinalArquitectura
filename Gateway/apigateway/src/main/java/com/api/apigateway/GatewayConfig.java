// GatewayConfig.java
package com.api.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final String vuelosServiceUrl = "http://localhost:8081";
    private final String viajesServiceUrl = "http://localhost:8082";
    private final String loginServiceUrl = "http://localhost:8084";
    private final String notificadorServiceUrl = "http://localhost:8085";

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("loginRoute", r -> r.path("/login")
                        .uri(loginServiceUrl))
                .route("vuelosRoute", r -> r.path("/ws/**")
                        .uri(vuelosServiceUrl))
                .route("clientesRoute", r -> r.path("/clientes")
                        .uri(viajesServiceUrl))
                .route("hotelesRoute", r -> r.path("/hoteles")
                        .uri(viajesServiceUrl))
                .route("viajesRoute", r -> r.path("/viajes")
                        .uri(viajesServiceUrl))
                .route("notificadorRoute", r -> r.path("/sendMail")
                        .uri(notificadorServiceUrl))
                .build();
    }
}