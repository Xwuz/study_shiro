package com.spring.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tiandao
 * @date 2018/9/4 17:07
 */
@Component
public class RedisCacheManager implements CacheManager {

    @Autowired
    private RedisCache redisCache;
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return redisCache;
    }
}
