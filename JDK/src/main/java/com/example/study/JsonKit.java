package com.example.study;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.util.Strings;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 统一的json实现。方便json实现方式的切换
 * @author gongqin(gongqin@dhgate.com)
 * @version 2019-06-21
 */
public class JsonKit {
    
    private static Gson gson = new GsonBuilder().setLenient().disableHtmlEscaping().create();

    public static String toJson(Object src) {
        return gson.toJson(src);
    }
    
    public static <T> T json2obj(String json, Class<T> eleType) {
        try {
            return gson.fromJson(json, eleType);
        } catch (Exception e) {
        }
        return null;
    }
    
    public static Map<String, Object> json2map(String json) {
        return fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
    }
    
    public static <T> List<T> json2list(String json, Class<T> eleType) {
        return fromJson(json, new TypeToken<ArrayList<T>>() {}.getType());
    }
    
    public static <T> T fromJson(String json, Type typeOfT) {
        if (Strings.isBlank(json))
            return null;
        try {
            return gson.fromJson(json, typeOfT);
        } catch (Exception e) {
        }
        return null;
    }
    
}
