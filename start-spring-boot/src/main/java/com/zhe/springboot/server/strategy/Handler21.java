package com.zhe.springboot.server.strategy;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Handler21 implements StrategyHandler<List<String>, String> {

    @Override
    public String apply(List<String> param) {
        return "Handler21";
    }
}
