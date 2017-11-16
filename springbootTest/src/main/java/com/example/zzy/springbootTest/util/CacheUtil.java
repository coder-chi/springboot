package com.example.zzy.springbootTest.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import com.example.zzy.springbootTest.constant.CommonSymbol;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class CacheUtil {

    private static final Cache<String, Object> MANAGER_TICKET_CACHE = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.DAYS).build();

    private static final Cache<String, Object> USER_TICKET_CACHE = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.DAYS).build();

    private static final Map<String, Cache> CACHE_MAP = ImmutableMap.of(CommonSymbol.USER_CACHE, USER_TICKET_CACHE, CommonSymbol.MANAGER_CACHE, MANAGER_TICKET_CACHE);

    public static void put(String cacheName, String key, Object value) {
        Cache<String, Object> cache = CACHE_MAP.get(cacheName);
        if (cache == null) {
            throw new RuntimeException();
        } else {
            cache.put(key, value);
        }
    }

    public static Object get(String cacheName, String key) {
        Cache<String, Object> cache = CACHE_MAP.get(cacheName);
        if (cache == null) {
            throw new RuntimeException();
        } else {
            return cache.getIfPresent(key);
        }
    }

}
