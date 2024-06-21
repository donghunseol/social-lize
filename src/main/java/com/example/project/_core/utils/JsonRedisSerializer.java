package com.example.project._core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonRedisSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // JavaTimeModule을 등록하여 LocalDate 등의 Java 8 날짜/시간 API를 처리할 수 있도록 설정
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static String serialize(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T deserialize(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }
}