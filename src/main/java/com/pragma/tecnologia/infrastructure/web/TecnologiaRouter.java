package com.pragma.tecnologia.infrastructure.web;

import com.pragma.tecnologia.infrastructure.requests.TecnologiaRequest;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springdoc.core.fn.builders.operation.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.function.Consumer;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;
import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;

@Configuration
public class TecnologiaRouter {

    private static final String TAG_TECNOLOGIA = "Tecnología";

    @Bean
    public RouterFunction<ServerResponse> tecnologiaRoutes(TecnologiaHandler handler) {
        return route()
                .POST("/api/tecnologia", handler::saveTecnologia, docSaveTecnologia())
                .GET("/api/tecnologia", handler::getAllTecnologias, docGetAllTecnologias())
                .GET("/api/tecnologia/{id}", handler::getTecnologiaById, docGetTecnologiaById())
                .DELETE("/api/tecnologia/{id}", handler::deleteTecnologia, docDeleteTecnologia())
                .build();
    }

    private Consumer<Builder> docSaveTecnologia() {
        return ops -> ops.tag(TAG_TECNOLOGIA)
                .operationId("saveTecnologia")
                .summary("Guardar nueva tecnología")
                .description("Registra una nueva tecnología en el catálogo.")
                .requestBody(requestBodyBuilder().implementation(TecnologiaRequest.class).required(true))
                .response(responseBuilder().responseCode("201").description("Creado exitosamente"))
                .response(responseBuilder().responseCode("400").description("Datos de entrada inválidos"));
    }

    private Consumer<Builder> docGetTecnologiaById() {
        return ops -> ops.tag(TAG_TECNOLOGIA)
                .operationId("getTecnologiaById")
                .summary("Obtener tecnología por ID")
                .parameter(parameterBuilder().in(ParameterIn.PATH).name("id").description("ID de la tecnología").example("123"))
                .response(responseBuilder().responseCode("200").description("Operación exitosa"))
                .response(responseBuilder().responseCode("404").description("Tecnología no encontrada"));
    }

    private Consumer<Builder> docGetAllTecnologias() {
        return ops -> ops.tag(TAG_TECNOLOGIA)
                .operationId("getAllTecnologias")
                .summary("Listar todas las tecnologías")
                .response(responseBuilder().responseCode("200").description("Lista de tecnologías"));
    }

    private Consumer<Builder> docDeleteTecnologia() {
        return ops -> ops.tag(TAG_TECNOLOGIA)
                .operationId("deleteTecnologia")
                .summary("Eliminar tecnología por ID")
                .description("Elimina una tecnología del sistema.")
                .parameter(parameterBuilder().in(ParameterIn.PATH).name("id").description("ID de la tecnología").example("123"))
                .response(responseBuilder().responseCode("204").description("Eliminado exitosamente"))
                .response(responseBuilder().responseCode("404").description("Tecnología no encontrada"));
    }
}
