package com.pragma.archetypespringboot.commons.configurations.traceability.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.pragma.archetypespringboot.commons.configurations.traceability.filter.UserCommand;
import lombok.experimental.UtilityClass;
import org.apache.tomcat.util.codec.binary.Base64;

@UtilityClass
public final class UtilToken {

    public static UserCommand usernameByToken(String authorization) {
        if (authorization == null || authorization.isEmpty()) {
            return null;
        }

        try {
            Base64 base64url = new Base64(true);

            String[] payloadEncoded = authorization.split("\\.");
            String payloadDecoded = new String(base64url.decode(payloadEncoded[1]), "UTF-8");

            ObjectMapper mapper = new ObjectMapper();

            UserCommand userDetails = mapper.readerFor(UserCommand.class).readValue(payloadDecoded);

            return userDetails;
        }
        catch (Exception e) {
            return null;
        }
    }

}
