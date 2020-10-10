package com.zhe.spring.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@Service
public class RedisServer {

    @Autowired
    private JedisPool jedisPool;

    public void hmset(String key, Map<String, String > map) {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.hmset(key, map);
        }
    }

    public Map<String, String> hgetall(String key) {
        try (Jedis jedis = jedisPool.getResource()){
            return jedis.hgetAll(key);
        }
    }

}
