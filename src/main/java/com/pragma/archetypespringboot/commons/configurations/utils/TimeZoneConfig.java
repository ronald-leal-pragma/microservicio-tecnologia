package com.pragma.archetypespringboot.commons.configurations.utils;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.TimeZone;

@Component
public class TimeZoneConfig {
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
        System.out.println("Zona horaria establecida: " + TimeZone.getDefault().getID());
    }
}
