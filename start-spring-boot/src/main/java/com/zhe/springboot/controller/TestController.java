package com.zhe.springboot.controller;

import com.zhe.springboot.server.strategy.RouterService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RouterService routerService;

    @GetMapping("/strategy")
    public String testStrategy(@RequestParam String p1, @RequestParam String p2) {
        List<String> param = Lists.newArrayList(p1, p2);
        return routerService.applyStrategy(param);
    }

}
