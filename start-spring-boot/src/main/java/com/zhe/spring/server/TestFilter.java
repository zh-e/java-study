package com.zhe.spring.server;

import com.zhe.spring.feature.Feature;
import com.zhe.spring.feature.JoinFilterContext;
import com.zhe.spring.util.JsonUtil;
import org.springframework.stereotype.Service;

@Service
public class TestFilter implements Feature<JoinFilterContext> {

    @Override
    public void filter(JoinFilterContext context) {
        System.out.println(JsonUtil.toJson(context));
        context.setFilter(true);
    }

}
