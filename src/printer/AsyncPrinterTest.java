
package printer;

import java.util.Random;
import printer.AsyncPrinter.Job;

/**
 *
 * @author julian
 */
public class AsyncPrinterTest {
    
    private static final int COUNT = 4;
    
    public static void main(String[] args) {
        
        AsyncPrinter printer = new AsyncPrinter();
        Thread thread = new Thread(printer, "printer");
        thread.start();
        
        PrintUser[] users = new PrintUser[COUNT];
        Random r = new Random();
        
        for (int i=0; i<COUNT; i++) {
            int pages = r.nextInt(4) + 1;
            long delay = 250 + r.nextInt(2000);
            users[i] = new PrintUser("" + (char)(i + 65), printer, delay, pages);
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
        private long wait;
        private int pages;
        private AsyncPrinter printer;
        
        public PrintUser(String username, AsyncPrinter printer, long wait, int pages) {
            this.username = username;
            this.printer = printer;
            this.wait = wait;
            this.pages = pages;
        }

        @Override
        public void run() {
            waitMillis(wait);
            Job job = printer.printDocument(username, pages);
            System.out.println("USER waiting for job " + job);            
            printer.waitForJob(job);            
            System.out.println("USER finished job " + job);
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
