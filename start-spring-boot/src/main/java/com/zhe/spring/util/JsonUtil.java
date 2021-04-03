package com.zhe.spring.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class JsonUtil {

    private final static ObjectMapper DEFAULT_MAPPER = new ObjectMapper();

    /**
     * 如果有null字段的话,就不输出,用在节省流量的情况
     */
    private final static ObjectMapper PURE_MAPPER = new ObjectMapper();

    /**
     * null字段也输出
     */
    private final static ObjectMapper RAW_MAPPER = new ObjectMapper();

    static {
        // 初始化
        DEFAULT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DEFAULT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        // 只对bean起作用，Map List不起作用
        DEFAULT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 指定类型的序列化, 不同jackson版本对不同的类型的默认规则可能不一样，这里做强制指定
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(java.sql.Date.class, new DateSerializer(null, new SimpleDateFormat("yyyy-MM-dd")));
        DEFAULT_MAPPER.registerModule(simpleModule);

        PURE_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    /**
     * json字符串到对象,默认配置
     *
     * @param json
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> valueType) {
        try {
            return DEFAULT_MAPPER.readValue(json, valueType);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * json字符串到对象,默认配置
     *
     * @param json
     * @param valueTypeRef 例如:new TypeReference<Map<String, Att>>(){}
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, TypeReference valueTypeRef) {
        try {
            return DEFAULT_MAPPER.readValue(json, valueTypeRef);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对象到json字符串,默认配置
     *
     * @param value
     * @return
     */
    public static String toJson(Object value) {
        try {
            return DEFAULT_MAPPER.writeValueAsString(value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * json字符串到对象,如果有null字段的话,就不输出,用在节省流量的情况
     *
     * @param json
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T fromJsonPure(String json, Class<T> valueType) {
        try {
            return PURE_MAPPER.readValue(json, valueType);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * json字符串到对象,如果有null字段的话,就不输出,用在节省流量的情况
     *
     * @param json
     * @param valueTypeRef 例如:new TypeReference<Map<String, Att>>(){}
     * @param <T>
     * @return
     */
    public static <T> T fromJsonPure(String json, TypeReference valueTypeRef) {
        try {
            return PURE_MAPPER.readValue(json, valueTypeRef);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对象到json字符串,如果有null字段的话,就不输出,用在节省流量的情况
     *
     * @param value
     * @return
     */
    public static String toJsonPure(Object value) {
        try {
            return PURE_MAPPER.writeValueAsString(value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对象到json字符串,有null字段的话也输出
     *
     * @param value
     * @return
     */
    public static String toJsonRaw(Object value) {
        try {
            return RAW_MAPPER.writeValueAsString(value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将 json 转成 JsonNode
     *
     * @param json
     * @return
     */
    public static JsonNode readTree(String json) {
        try {
            return DEFAULT_MAPPER.readTree(json);
        } catch (IOException e) {
            return null;
        }
    }

}
