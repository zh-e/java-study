package com.zhe.spring.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhe.spring.annotation.SessionKeyValidate;
import com.zhe.spring.bean.Demo;
import com.zhe.spring.bean.Table;
import com.zhe.spring.feature.FeatureService;
import com.zhe.spring.feature.JoinFilterContext;
import com.zhe.spring.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

//    @Autowired
//    private RedisServer redisServer;

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

    @GetMapping("/test-session")
    @SessionKeyValidate
    public String testSession() {
        return "test session";
    }
//
//    @PostMapping
//    public String redis(@RequestParam String key) {
//        Map<String, String> map = new HashMap<>();
//        map.put("1", "1");
//        map.put("2", "1");
//        map.put("3", "1");
//        map.put("4", "1");
//        map.put("5", "1");
//        map.put("6", "1");
//
//        redisServer.hmset(key, map);
//        return "success";
//
//    }
//
//    @GetMapping("/test")
//    public String test(@RequestParam String key) {
//        Table table = new Table();
//
//        table.setRoomId("111");
//        table.setName("jjj");
//        table.setSubType(0);
//        table.setMatchId("uuuuuu");
//        table.setPlayers("uuiioo99943");
//        table.setStage(1);
//        table.setTableStatus(9);
//        table.setTableCards("jjioo");
//        table.setActionSid("111");
//        table.setTimeOut("3rwqr");
//        table.setButtonSid("1221");
//        table.setRound("21");
//        table.setStartTime(System.currentTimeMillis());
//        table.setCountDown(System.currentTimeMillis());
//
//        Map<String, String> map = JsonUtil.fromJson(JsonUtil.toJson(table), new TypeReference<Map<String, String>>(){});
//
//        redisServer.hmset(key, map);
//
//        Map<String, String> res = redisServer.hgetall(key);
//
//        Table table1 = JsonUtil.fromJson(JsonUtil.toJson(res), Table.class);
//
//        return JsonUtil.toJson(table1);
//    }

    @RequestMapping("/hello")
    public String get(Demo demo) {
        System.out.println(demo.getName());
        return "";
    }

}
