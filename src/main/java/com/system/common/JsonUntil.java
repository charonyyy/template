package com.system.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsonUntil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String parseObject(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.info("Serialization failed!", e);
        }
        return "";
    }
}
