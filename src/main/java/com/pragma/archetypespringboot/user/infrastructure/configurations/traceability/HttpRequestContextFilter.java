package com.pragma.archetypespringboot.user.infrastructure.configurations.traceability;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static com.pragma.archetypespringboot.user.infrastructure.configurations.traceability.UtilConverter.toMapConverter;
import static com.pragma.archetypespringboot.user.infrastructure.configurations.traceability.UtilGenerateUnique.generateGeneralRequestId;
import static com.pragma.archetypespringboot.user.infrastructure.configurations.traceability.UtilGenerateUnique.generateRequestIdMicroservice;
import static com.pragma.archetypespringboot.user.infrastructure.configurations.traceability.UtilToken.usernameByToken;

@Component
@Slf4j
public class HttpRequestContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        RequestWrapper wrapper = new RequestWrapper((HttpServletRequest) request);
        String autorization = request.getHeader("Authorization");

        try {

            String url = UriUtils.decode(request.getRequestURI(), "UTF-8");
            String idGeneralRequest = request.getHeader("idGeneralRequest");
            Map<String, List<String>> parameterUrl = toMapConverter(request.getParameterMap());

            byte[] requestBody = StreamUtils.copyToByteArray(wrapper.getInputStream());
            String bodyDecode = new String(requestBody, StandardCharsets.UTF_8);
            if (bodyDecode.isBlank() || bodyDecode.isEmpty()) {
                bodyDecode = null;
            }
            UserCommand userCommand = usernameByToken(autorization);

            String email = null;

            if (userCommand != null) {
                email = userCommand.getEmail();
            }

            RequestContextDto requestContextDto = new RequestContextDto(
                    autorization,
                    bodyDecode,
                    url,
                    parameterUrl,
                    email,
                    generateGeneralRequestId(idGeneralRequest),
                    generateRequestIdMicroservice());
            HttpRequestContextHolder.setRequestHolder(requestContextDto);
        }
        catch (Exception e) {
            RequestContextDto requestContextDto = RequestContextDto
                    .builder()
                    .token(autorization)
                    .build();
            HttpRequestContextHolder.setRequestHolder(requestContextDto);
        }
        filterChain.doFilter(wrapper, response);
    }

}
