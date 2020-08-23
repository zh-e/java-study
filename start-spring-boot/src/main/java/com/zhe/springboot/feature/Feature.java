package com.zhe.springboot.feature;

/**
 * 扩展点接口
 * <p>Created by dongliang 2019-10-15</p>
 */
public interface Feature<C extends Feature.Context> {

    interface Context {}

    void filter(C context);

}
