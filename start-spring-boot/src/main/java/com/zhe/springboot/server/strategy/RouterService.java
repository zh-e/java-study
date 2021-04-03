package com.zhe.springboot.server.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouterService extends AbstractStrategyRouter<List<String>, String> {

    @Autowired
    private RouterHandler1 routerHandler1;

    @Autowired
    private RouterHandler2 routerHandler2;

    @Override
    protected StrategyMapper<List<String>, String> registerStrategyMapper() {
        return param -> {
            String p = param.get(0);
            if ("p1".equals(p)) {
                return routerHandler1;
            }
            if ("p2".equals(p)) {
                return routerHandler2;
            }
            return null;
        };
    }

}
