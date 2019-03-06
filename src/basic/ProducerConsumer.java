package basic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author julian
 */
public class ProducerConsumer {
    
    static final int COUNT = 10;

    public static void main(String[] args) throws InterruptedException {

        Buffer buffer = new Buffer(3);
        Random r = new Random();

        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int count = 0;
                    while (count < COUNT) {
                        buffer.add(count);
                        System.out.println("Produced " + count);
                        count++;
                        Thread.sleep(250 + r.nextInt(2500));
                    }
                    
                    System.out.println("Ended producer");
                    
                } catch (InterruptedException e) {
                    System.out.println("producer interrupted!");
                }
            }
        });

        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int count = 0;
                    while (count < COUNT) {
                        int value = buffer.poll();
                        System.out.println("Consume " + value);  
                        count ++;
                        Thread.sleep(250 + r.nextInt(2500));
                    }
                    
                    System.out.println("Ended consumer");
                    
                } catch (InterruptedException e) {
                    System.out.println("consumer interrupted!");
                }
            }
        });
        
        producerThread.start();
        consumerThread.start();
        
        producerThread.join();
        consumerThread.join();
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
