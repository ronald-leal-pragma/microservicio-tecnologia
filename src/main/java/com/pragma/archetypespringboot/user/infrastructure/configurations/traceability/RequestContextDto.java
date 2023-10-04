package com.pragma.archetypespringboot.user.infrastructure.configurations.traceability;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class RequestContextDto {

    private String token;
    private String body;
    private String url;
    private Map<String, List<String>> parametersUrl;
    private String emailRequest;
    private String idGeneralRequest;
    private String idRequest;

}
