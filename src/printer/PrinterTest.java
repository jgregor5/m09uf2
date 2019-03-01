
package printer;

import java.util.Random;

/**
 *
 * @author julian
 */
public class PrinterTest {    
    
    private static final int COUNT = 4;
    
    public static void main(String[] args) {
        
        Printer printer = new Printer();                
        PrintUser[] users = new PrintUser[COUNT];
        Random r = new Random();
        
        for (int i=0; i<COUNT; i++) {
            int pages = r.nextInt(5);
            long delay = 250 + r.nextInt(2000);
            users[i] = new PrintUser("" + (char)(i + 65), printer, delay, pages);
        }
        
        Thread[] threads = new Thread[COUNT];
        for (int i=0; i<COUNT; i++) {
            threads[i] = new Thread(users[i], "user-" + i);
            threads[i].start();
        }
        
        for (int i=0; i<COUNT; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    static class PrintUser implements Runnable {
        
        private String username;
        private long wait;
        private int pages;
        private Printer printer;
        
        public PrintUser(String username, Printer printer, long wait, int pages) {
            this.username = username;
            this.printer = printer;
            this.wait = wait;
            this.pages = pages;
        }

        @Override
        public void run() {
            waitMillis(wait);
            printer.printDocument(username, pages);
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
