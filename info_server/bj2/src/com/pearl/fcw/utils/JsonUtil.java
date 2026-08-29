package com.pearl.fcw.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static List<?> readValueList(String json, Class<?> clazz) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
            return (List<?>)mapper.readValue(json, javaType);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String writeAsString(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String writeAsStringPretty(Object value) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}
