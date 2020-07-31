//package com.zhe.springboot.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Component
//public class RedisConfig {
//
//
//    @Value("${redis.server.host}")
//    private String host;
//
//    @Value("${redis.server.port}")
//    private int port;
//
//    public JedisPoolConfig getRedisConfig() {
//        return new JedisPoolConfig();
//    }
//
//    @Bean
//    public JedisPool getJedisPool() {
//        JedisPoolConfig config = getRedisConfig();
//        return new JedisPool(config, host, port, 2000);
//    }
//
//}
