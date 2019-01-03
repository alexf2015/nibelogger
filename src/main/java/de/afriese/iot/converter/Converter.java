/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.afriese.iot.converter;

import static de.afriese.iot.converter.ResponseType.NIBE_UPLINK;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Converter {

    public final Map<String, String> convertJsonResponse(ResponseType type, String json) {
        return convertJsonResponse(type, null, json);
    }

    public final Map<String, String> convertJsonResponse(ResponseType type, String itemId, String json) {

        switch (type) {
            case NIBE_UPLINK:
                return convertJson(json, NIBE_UPLINK.getResponseClass()).getValues();
            default:
                log.warn("unknown type value");
                return null;
        }

    }

    private <T extends DataResponse> T convertJson(String jsonInString, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        T obj = null;
        try {
            log.debug(jsonInString);
            obj = mapper.readValue(jsonInString, clazz);
        }
        catch (IOException e) {
            log.error(e.getMessage());
        }
        return obj;
    }
}
