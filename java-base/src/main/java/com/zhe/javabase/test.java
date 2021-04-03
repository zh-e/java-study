package com.zhe.javabase;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class test {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DiceUtil.class.getSimpleName())
                .forks(1)
                .threads(30)
                .measurementIterations(1)
                .build();

        new Runner(opt).run();
    }

}
