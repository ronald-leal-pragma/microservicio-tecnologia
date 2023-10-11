package com.pragma.archetypespringboot.generalconfigurations.traceability;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TraceabilityDto {

    private String application;

    @JsonProperty("input_body")
    private String inputBody;

    @JsonProperty("input_parameters")
    private String inputParameters;

    @JsonProperty("general_request_id")
    private String generalRequestId;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("email_user")
    private String emailUser;

    @JsonProperty("url_endpoint")
    private String urlEndpoint;

    @JsonProperty("class_name")
    private String className;

    @JsonProperty("method_name")
    private String methodName;

    private String action;

    private String microservice;

    @JsonProperty("trace_error")
    private String traceError;

    @JsonProperty("creation_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm:ss .SSS")
    private LocalDateTime creationDate;


    @JsonProperty("environment")
    private String environment;

    public String toString() {
        return "TraceabilityDto{" +
                "application='" + application + '\'' +
                ", inputBody='" + inputBody + '\'' +
                ", inputParameters='" + inputParameters + '\'' +
                ", generalRequestId='" + generalRequestId + '\'' +
                ", requestId='" + requestId + '\'' +
                ", emailUser='" + emailUser + '\'' +
                ", urlEndpoint='" + urlEndpoint + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", action='" + action + '\'' +
                ", microservice='" + microservice + '\'' +
                ", creationDate=" + creationDate +
                ", environment='" + environment + '\'' +
                '}';
    }

}
