package com.example.zzy.springbootTest.util;

import com.alibaba.fastjson.JSON;

/**
 * Created by chloe on 2017/5/15.
 */
public class JsonUtil {

    public static Object json2Obj(String json, Class clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static String obj2JsonString(Object obj) {
        return JSON.toJSONString(obj);
    }

}
