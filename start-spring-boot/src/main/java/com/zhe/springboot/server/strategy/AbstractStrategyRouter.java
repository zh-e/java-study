package com.zhe.springboot.server.strategy;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public abstract class AbstractStrategyRouter<T, R> {

    public interface StrategyMapper<T, R> {
        StrategyHandler<T, R> get(T param);
    }

    private StrategyMapper<T, R> strategyMapper;

    @PostConstruct
    private void abstractInit() {
        strategyMapper = registerStrategyMapper();
        Objects.requireNonNull(strategyMapper, "StrategyMapper cannot bu null");
    }

    @SuppressWarnings("unchecked")
    private final StrategyHandler<T, R> defaultStrategyHandler = StrategyHandler.DEFAULT;

    public R applyStrategy(T param) {
        final StrategyHandler<T, R> strategyHandler = strategyMapper.get(param);
        if (strategyHandler != null) {
            return strategyHandler.apply(param);
        }
        return defaultStrategyHandler.apply(param);
    }

    protected abstract StrategyMapper<T, R> registerStrategyMapper();

}


