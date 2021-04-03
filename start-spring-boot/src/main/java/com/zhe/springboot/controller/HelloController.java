package com.zhe.springboot.controller;

import com.zhe.springboot.feature.FeatureService;
import com.zhe.springboot.feature.JoinFilterContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangzhe
 */
@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @Autowired
    private FeatureService featureService;

    @GetMapping("/str")
    public String hello() {

        JoinFilterContext joinFilterContext = JoinFilterContext.builder()
                .gameType(1111)
                .teamId(11)
                .subType(1)
                .uid("222").build();

        featureService.filter(joinFilterContext);

        if (joinFilterContext.isFilter()) {
            return "111111";
        } else {
            return "22222";
        }
    }



}
