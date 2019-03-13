package basic;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author julian
 */
public class BufferTest implements Runnable {
    
    private static final int COUNT = 10;
    private static final int SIZE = 5;
    
    private boolean stop, end;
    private final BlockingQueue<Job> buffer;
    
    public BufferTest() {
        this.buffer = new ArrayBlockingQueue<>(SIZE); 
        this.stop = false;
        this.end = false;
    }
    
    public void add(Job job) {
        try {
            // difference between offer, put, add?
            this.buffer.put(job);            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        while (!end) {
            Job job = this.buffer.poll();
            if (job != null) {
                System.out.println("executing square for " + job.input);
                try { Thread.sleep(250); } catch (InterruptedException ex) {}
                job.set(job.input * job.input);
            }
            else { // empty
                if (stop) {
                    end = true;
                }
                try { Thread.sleep(100); } catch (InterruptedException ex) {}
            }
        }
    }
    
    public void stop() {
        this.stop = true;
    }
    
    public static void main(String[] args) throws InterruptedException {        

        BufferTest bt = new BufferTest();
        Thread thread = new Thread(bt);
        thread.start();
        
        Job[] jobs = new Job[COUNT];
        for (int i=0; i<COUNT; i++) {
            jobs[i] = new Job(i);
            bt.add(jobs[i]);
            System.out.println("queued " + i);
        }

        bt.stop();
    }
    
    static class Job {
        
        private int input, output;
        
        public Job(int input) { 
            this.input = input; 
        }
        
        public void set(int output) {
            System.out.println("result is " + output);
            this.output = output;
        }
    }
}
