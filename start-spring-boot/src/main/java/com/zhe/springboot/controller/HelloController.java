package com.zhe.springboot.controller;

import com.zhe.springboot.annotation.SessionKeyValidate;
import com.zhe.springboot.server.RedisServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhe
 */
@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @Autowired
    private RedisServer redisServer;

    @GetMapping("/str")
    public String hello() {
        return "hello str";
    }

    @GetMapping("/test-session")
    @SessionKeyValidate
    public String testSession() {
        return "test session";
    }

    @PostMapping
    public String redis(@RequestParam String key) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "1");
        map.put("3", "1");
        map.put("4", "1");
        map.put("5", "1");
        map.put("6", "1");

        redisServer.hmset(key, map);
        return "success";

    }

    @GetMapping("/redisGet")
    public String redisGet(@RequestParam String key) {
        return redisServer.get(key);
    }

}
