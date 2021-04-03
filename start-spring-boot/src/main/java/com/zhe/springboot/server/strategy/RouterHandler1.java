package com.zhe.springboot.server.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouterHandler1 extends AbstractStrategyRouter<List<String>, String> implements StrategyHandler<List<String>, String> {

    @Autowired
    private Handler11 handler11;

    @Autowired
    private Handler12 handler12;

    @Override
    protected StrategyMapper<List<String>, String> registerStrategyMapper() {
        return param -> {
            String p = param.get(1);
            if ("h1".equals(p)) {
                return handler11;
            }
            if ("h2".equals(p)) {
                return handler12;
            }
            return null;
        };
    }

    @Override
    public String apply(List<String> param) {
        return this.applyStrategy(param);
    }
}
