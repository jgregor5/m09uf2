package basic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author julian
 */
public class ProducerConsumer {

    public static void main(String[] args) throws InterruptedException {

        Buffer buffer = new Buffer(3);
        Random r = new Random();

        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int value = 0;
                    while (value < 10) {
                        buffer.add(value);
                        System.out.println("Produced " + value);
                        value++;
                        Thread.sleep(250 + r.nextInt(2500));
                    }
                } catch (InterruptedException e) {
                    System.out.println("producer interrupted!");
                }
            }
        });

        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        int value = buffer.poll();
                        System.out.println("Consume " + value);                        
                        Thread.sleep(250 + r.nextInt(2500));
                    }
                } catch (InterruptedException e) {
                    System.out.println("consumer interrupted!");
                }
            }
        });
        
        producerThread.start();
        consumerThread.start();
        
        producerThread.join();
        consumerThread.interrupt();
    }

    static class Buffer {

        private Queue<Integer> list;
        private int size;

        public Buffer(int size) {
            this.list = new LinkedList<>();
            this.size = size;
        }

        public void add(int value) throws InterruptedException {

            synchronized (this) {
                while (list.size() >= size) {
                    wait();
                }

                list.add(value);
                notify();
            }
        }

        public int poll() throws InterruptedException {

            synchronized (this) {
                while (list.isEmpty()) {
                    wait();
                }

                int value = list.poll();
                notify();
                return value;
            }
        }
    }
}
