package com.zhe.javabase.Thread;

/**
 * 竞态demo
 * @author zhangzhe
 */
public class RaceConditionDemo {

    public static void main(String[] args) {

        int numOfThreads = args.length > 0 ? Short.valueOf(args[0]) : Runtime.getRuntime().availableProcessors();

        Thread[] workerThreads = new Thread[numOfThreads];

        for(int i = 0; i < numOfThreads; i++) {
            workerThreads[i] = new WorkerThread(i, 10);
        }

        for(Thread ct : workerThreads) {
            ct.start();
        }

    }

    static class WorkerThread extends Thread {
        private final int requestCount;

        public WorkerThread(int id, int requestCount) {
            super("worker-" + id);
            this.requestCount = requestCount;
        }

        @Override
        public void run() {
            int i = requestCount;
            String requestID;

            RequestIDGenerator idGenerator = RequestIDGenerator.getInstance();

            while (i-- > 0) {
                requestID = idGenerator.nextID();
                processRequest(requestID);
            }
        }

        private void processRequest(String requestID) {
            try {
                Thread.sleep((int)(Math.random() * 50));
            } catch (Exception e) {
                //
            }
            System.out.printf("%s get requestID: %s %n", Thread.currentThread().getName(), requestID);

        }
    }

}
