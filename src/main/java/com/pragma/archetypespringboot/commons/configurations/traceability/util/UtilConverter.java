package com.pragma.archetypespringboot.commons.configurations.traceability.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public final class UtilConverter {

    public static Map<String, List<String>> toMapConverter(Map<String, String[]> mapIn) {
        Map<String, List<String>> mapConverter = new HashMap<>();
        mapIn.forEach((key, value) -> mapConverter.put(key, Arrays.asList(value)));
        return mapConverter;
    }


}
