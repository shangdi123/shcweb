/* ******************************************************
 * Copyright (C) 2018 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-script.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Author(s): han yunlong <hanyunlong@qiyi.com>
 * 2018/8/9
 * ******************************************************/
package com.hyl.qixiao.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings("unchecked")
    public static Map<String, Object> obj2Map(Object bean) {
        return mapper.convertValue(bean, Map.class);
    }

    public static String obj2Str(Object bean) {
        try {
            return mapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> str2Map(String jsonStr) {
        try {
            return mapper.readValue(jsonStr, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T str2Obj(String jsonStr, Class<T> type) {
        try {
            return mapper.readValue(jsonStr, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T str2Obj(String jsonStr, TypeReference type) {
        try {
            return mapper.readValue(jsonStr, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> str2List(String jsonStr, Class<T> elementType) {
        try {
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, elementType);
            return mapper.readValue(jsonStr, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String map2Str(Map<String, Object> map) {
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException je) {
            throw new RuntimeException(je);
        }
    }

    public static JsonNode str2Node(String jsonStr) {
        try {
            return mapper.readTree(jsonStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
