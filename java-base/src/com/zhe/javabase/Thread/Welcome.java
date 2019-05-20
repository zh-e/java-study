package com.zhe.javabase.Thread;

import java.util.concurrent.*;

/**
 * @author zhangzhe
 */
public class Welcome {

    public static void main(String[] args) {

        Thread welComeThread = new WelcomeThread();
        welComeThread.setName("welcomeThread");
        welComeThread.start();

        welComeThread = new Thread(new WelcomeTask());
        welComeThread.setName("welcomeTask");
        welComeThread.start();

        Callable welCome = new WelcomeCall();
        ExecutorService executorService = Executors.newWorkStealingPool();
        executorService.submit(welCome);
        executorService.shutdown();

        CompletableFuture.runAsync(new WelcomeTask());

        System.out.println("WelCome.main:" + Thread.currentThread().getName());

    }

}

class WelcomeThread extends Thread {
    @Override
    public void run() {
        System.out.println("WelComeThread.run:" + Thread.currentThread().getName());
    }
}

class WelcomeTask implements Runnable {
    @Override
    public void run() {
        System.out.println("WelcomeTask.run:" + Thread.currentThread().getName());
    }
}

class WelcomeCall implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("WelcomeCall.call:" + Thread.currentThread().getName());
        return null;
    }
}