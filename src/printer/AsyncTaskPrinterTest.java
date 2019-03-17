
package printer;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import printer.AsyncTaskPrinter.Job;

/**
 *
 * @author julian
 */
public class AsyncTaskPrinterTest {
    
    private static final int COUNT = 4;
    
    public static void main(String[] args) {
        
        AsyncTaskPrinter printer = new AsyncTaskPrinter();
        
        PrintUser[] users = new PrintUser[COUNT];
        Random r = new Random();
        
        for (int i=0; i<COUNT; i++) {
            int pages = r.nextInt(4) + 1;
            long delay = 250 + r.nextInt(2000);            
            long delay2 = r.nextInt(2) > 0?  250 + r.nextInt(2000) : 0;
            users[i] = new PrintUser("" + (char)(i + 65), printer, delay, delay2, pages);
        }
        
        Thread[] threads = new Thread[COUNT];
        for (int i=0; i<COUNT; i++) {
            threads[i] = new Thread(users[i], "user-" + i);
            threads[i].start();
        }
        
        // main espera que acabin els usuaris
        
        for (int i=0; i<COUNT; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        printer.stop();
    }
    
    static class PrintUser implements Runnable {
        
        private String username;
        private long wait, cancelWait;
        private int pages;
        private AsyncTaskPrinter printer;
        
        public PrintUser(String username, AsyncTaskPrinter printer, long wait, long cancelWait, int pages) {
            this.username = username;
            this.printer = printer;
            this.wait = wait;
            this.cancelWait = cancelWait;
            this.pages = pages;
        }

        @Override
        public void run() {
            waitMillis(wait);
            Future<Job> future = printer.printDocument(username, pages);
                
            if (cancelWait > 0) {
                // try to cancel it
                waitMillis(cancelWait);
                boolean done = future.cancel(false);
                System.out.println("cancelling of job for " + username + " done: " + done);
            }
            else {
                try {
                    System.out.println("USER " + username + " waiting for a job");
                    Job job = future.get();
                    System.out.println("USER " + username + " finished job " + job);

                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }                
            }
        }
    }
    
    private static void waitMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
