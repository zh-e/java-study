package com.zhe.springboot.util;

import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

public class DiceUtil {

    static ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    public static int dice() {
        int n = threadLocalRandom.nextInt(1, 100);
        if (n < 50) {
            return 6;
        } else {
            return threadLocalRandom.nextInt(1, 7);
        }
    }


    public static int dice1() {
        Random random = new Random();
        int n = random.nextInt(100);
        if (n < 50) {
            return 6;
        } else {
            return random.nextInt(7);
        }
    }

    public static int dice2() {
        int n = (int)(Math.random()*100);
        if (n < 50) {
            return 6;
        } else {
            return (int)(Math.random()*6);
        }
    }

    public static int dice3() {
        SplittableRandom random = new SplittableRandom();
        int n = random.nextInt(100);
        if (n < 50) {
            return 6;
        } else {
            return random.nextInt(7);
        }
    }

}
