package com.zhe.spring.feature;

public interface Feature<C extends Feature.Context> {

    interface Context {}

    void filter(C context);

}
