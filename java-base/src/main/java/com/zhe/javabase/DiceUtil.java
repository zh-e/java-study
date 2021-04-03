package com.zhe.javabase;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

public class DiceUtil {

    static ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    @Benchmark
    public static int dice4() {
        ThreadLocalRandom threadLocalRandom1 = ThreadLocalRandom.current();
        return threadLocalRandom1.nextInt(1, 7);
    }

    @Benchmark
    public static int dice() {
        return threadLocalRandom.nextInt(1, 7);
    }



//    @Benchmark
    public static int dice1() {
        Random random = new Random();
        return random.nextInt(100);
    }

//    @Benchmark
    public static int dice2() {
        return (int)(Math.random()*100);
    }

//    @Benchmark
    public int dice3() {
        SplittableRandom random = new SplittableRandom();
        return random.nextInt(100);
    }

}
