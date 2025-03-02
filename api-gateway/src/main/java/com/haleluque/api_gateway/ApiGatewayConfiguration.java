package com.haleluque.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    /**
     * Bean that will handle custom routing conversion
     *
     * @param builder
     * @return RouteLocator
     */
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange")) //<-- lb:// - load balance as well
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion/**")
                        .filters(f -> f.rewritePath(
                                "/currency-conversion/(?<segment>.*)",
                                "/currency-conversion/${segment}"))
                        .uri("lb://currency-conversion"))
                .build();
    }
}
