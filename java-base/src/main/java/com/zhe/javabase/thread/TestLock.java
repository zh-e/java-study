package com.zhe.javabase.thread;

public class TestLock {

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (TestLock.class) {
                while (true) {
                    System.out.println("class synchronized");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            a();
        }).start();

    }

    private synchronized static void a() {
        while (true) {
            System.out.println("function synchronized");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
