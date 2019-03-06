package basic;

import java.util.Random;

/**
 *
 * @author julian
 */
public class WaitFirstThreadTest {
 
    static final int COUNT = 5;
    
    public static void main(String[] args) throws InterruptedException {
        
        Result result = new Result();        
        Thread[] threads = new Thread[COUNT];
        
        for (int i=0; i<COUNT; i++) {
            Worker worker = new Worker(result, i);
            threads[i] = new Thread(worker, "worker-" + i);
            threads[i].start();            
        }
        
        System.out.println("MAIN: wait for one thread...");
        synchronized (result) {
            while (result.get() == 0)
                result.wait();
        }        
        
        System.out.println("MAIN: result: " + result.get());
        for (int i=0; i<COUNT; i++) {
            threads[i].interrupt();
        }
    }
 
    static class Result {
        
        private int id;
        
        public Result() {
            this.id = 0;
        }
        
        public void set(int id) {
            this.id = id;
        }
        
        public int get() {
            return id;
        }
    }
    
    static class Worker implements Runnable {
        
        private final int id;
        private final Result result;
        
        public Worker(Result result, int id) {
            this.result = result;
            this.id = id;
        }

        @Override
        public void run() {    
            
            try {
                Random r = new Random();
                Thread.sleep(r.nextInt(10000));
            } catch (InterruptedException ex) {
                System.out.println("WORKER: interrupted " + id);
                return;
            }
            
            System.out.println("WORKER: finished " + id);
            synchronized (result) {
                result.notify();
                result.set(id);
            }
            System.out.println("WORKER: notified " + id);
        }
    }
}
