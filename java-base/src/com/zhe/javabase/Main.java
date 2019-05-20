package com.zhe.javabase;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangzhe
 */
public class Main {

    public static void main(String[] args) {
        HelloInterface h = new HelloInterface() {
            @Override
            public void hello() {
                System.out.println("hello");
            }
        };

        h.hello();
    }

}
