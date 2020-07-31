package com.zhe.springboot.controller;

import com.zhe.springboot.annotation.SessionKeyValidate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangzhe
 */
@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping("/str")
    public String hello() {
        return "hello str";
    }

    @GetMapping("/test-session")
    @SessionKeyValidate
    public String testSession() {
        return "test session";
    }

}
