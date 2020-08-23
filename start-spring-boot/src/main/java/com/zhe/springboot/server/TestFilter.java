package com.zhe.springboot.server;

import com.zhe.springboot.feature.Feature;
import com.zhe.springboot.feature.JoinFilterContext;
import com.zhe.springboot.util.JsonUtil;
import org.springframework.stereotype.Service;

@Service
public class TestFilter implements Feature<JoinFilterContext> {

    @Override
    public void filter(JoinFilterContext context) {
        System.out.println(JsonUtil.toJson(context));
        context.setFilter(true);
    }

}
