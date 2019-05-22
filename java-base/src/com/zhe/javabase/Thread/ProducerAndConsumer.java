package com.zhe.javabase.Thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 多线程生产者消费者问题
 * @author zhangzhe
 */
public class ProducerAndConsumer {

    public static void main(String[] args) {
        final Queue<Integer> queue = new LinkedList<>();

        ProducerAndConsumer pac = new ProducerAndConsumer();
        Thread producer = pac.initProducer(queue);
        Thread consumer = pac.initConsumer(queue);

        producer.start();
        consumer.start();
    }

    private Producer initProducer(Queue<Integer> sharedQ) {
        return new Producer(sharedQ);
    }

    private Consumer initConsumer(Queue<Integer> sharedQ) {
        return new Consumer(sharedQ);
    }

    class Producer extends Thread {

        private final Queue<Integer> sharedQ;

        public Producer(Queue<Integer> sharedQ) {
            super("Producer");
            this.sharedQ = sharedQ;
        }

        @Override
        public void run() {
            for(int i = 0; i< 20; i++) {
                synchronized (sharedQ) {
                    while (sharedQ.size() >= 1) {
                        try {
                            System.out.println("Queue is full, waiting");
                            sharedQ.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("producing : " + i);
                    sharedQ.add(i);
                    sharedQ.notify();
                }
            }
        }
    }

    class Consumer extends Thread {

        private final Queue<Integer> sharedQ;

        public Consumer(Queue<Integer> sharedQ) {
            this.sharedQ = sharedQ;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (sharedQ) {
                    while (sharedQ.size() == 0) {
                        try {
                            System.out.println("Queue is empty, waiting");
                            sharedQ.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int num = sharedQ.poll();
                    System.out.println("consuming : " + num);
                    sharedQ.notify();
                }
            }
        }
    }


}
