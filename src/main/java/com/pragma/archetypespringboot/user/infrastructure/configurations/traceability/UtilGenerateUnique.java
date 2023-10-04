package com.pragma.archetypespringboot.user.infrastructure.configurations.traceability;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UtilGenerateUnique {

    public static String generateGeneralRequestId (String idPeticionGeneral) {
        if (idPeticionGeneral == null || idPeticionGeneral.isEmpty() || idPeticionGeneral.isBlank()) {
            return UUID.randomUUID().toString();
        }
        return idPeticionGeneral;
    }

    public static String generateRequestIdMicroservice() {
        return UUID.randomUUID().toString();
    }

}
