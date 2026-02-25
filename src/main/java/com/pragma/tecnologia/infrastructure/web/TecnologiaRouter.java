package com.pragma.tecnologia.infrastructure.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TecnologiaRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(TecnologiaHandler handler) {
        return route(POST("/api/tecnologia").and(accept(MediaType.APPLICATION_JSON)), handler::saveTecnologia)
                .andRoute(GET("/api/tecnologia/{id}"), handler::getTecnologiaById);
    }
}
