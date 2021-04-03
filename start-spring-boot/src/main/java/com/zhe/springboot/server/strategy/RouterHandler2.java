package com.zhe.springboot.server.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouterHandler2 extends AbstractStrategyRouter<List<String>, String> implements StrategyHandler<List<String>, String> {

    @Autowired
    private Handler21 handler21;

    @Autowired
    private Handler22 handler22;

    @Override
    protected StrategyMapper<List<String>, String> registerStrategyMapper() {
        return param -> {
            String p = param.get(1);
            if ("h1".equals(p)) {
                return handler21;
            }
            if ("h2".equals(p)) {
                return handler22;
            }
            return null;
        };
    }

    @Override
    public String apply(List<String> param) {
        return this.applyStrategy(param);
    }

}
