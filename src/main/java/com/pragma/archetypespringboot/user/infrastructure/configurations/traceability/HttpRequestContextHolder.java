package com.pragma.archetypespringboot.user.infrastructure.configurations.traceability;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpRequestContextHolder {
    private static final ThreadLocal<RequestContextDto> REQUEST_HOLDER = new ThreadLocal<>();

    public static void setRequestHolder(RequestContextDto authorization) {
        REQUEST_HOLDER.set(authorization);
    }

    public static RequestContextDto getRequestHolder() {
        return REQUEST_HOLDER.get();
    }

    public static boolean isNotNull() {
        return REQUEST_HOLDER.get() != null;
    }

    public static void clearHolder() {
        REQUEST_HOLDER.remove();
    }
}
