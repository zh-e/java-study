package com.zhe.javabase.Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhangzhe
 */
public class ReadWriteLookDemo {

    private static int n = 100;

    private final static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final static Lock readLock = readWriteLock.readLock();
    private final static Lock writeLock = readWriteLock.writeLock();


    public static void main(String[] args) {

        int num = Runtime.getRuntime().availableProcessors();

        for(int i = 0; i < num; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    writeLockTest();
                }
            });

            thread.start();
        }

    }

    private static void readLockTest() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "@@@" + System.currentTimeMillis());
            Thread.sleep(1000);
        } catch (Exception e) {
            //
        } finally {
            readLock.unlock();
        }
    }

    private static void writeLockTest() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "@@@" + System.currentTimeMillis());
            Thread.sleep(1000);
        } catch (Exception e) {
            //
        } finally {
            writeLock.unlock();
        }
    }

}
