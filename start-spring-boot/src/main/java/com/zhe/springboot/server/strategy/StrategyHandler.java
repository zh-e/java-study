package com.zhe.springboot.server.strategy;

public interface StrategyHandler<T, R> {

    @SuppressWarnings("rawtypes")
    StrategyHandler DEFAULT = t -> null;

    R apply(T param);

}
