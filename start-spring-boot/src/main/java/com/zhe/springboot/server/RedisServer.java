package com.zhe.springboot.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisServer {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void hmset(String key, Map<String, String > map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

//    @Autowired
//    private JedisPool jedisPool;
//
//    public void hmset(String key, Map<String, String> map) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            jedis.hmset(key, map);
//        }
//
//    }

}
