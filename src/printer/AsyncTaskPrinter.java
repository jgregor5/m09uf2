package printer;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 *
 * @author julian
 */
public class AsyncTaskPrinter {
    
    // use this printer to print!
    private final Printer printer;
    
    public AsyncTaskPrinter() {
        this.printer = new Printer();
        // completar
    }
        
    public Future<Job> printDocument(String username, int pages) {
        throw new RuntimeException("no implementat");
    }
    
    public void stop() {
        throw new RuntimeException("no implementat");
    }
    
    static class Job {
        
        public Job(int id, String username, int pages) {
            // completar
        }
        
        // completar
    }    
    
    static class PrintJob implements Callable<Job> {

        @Override
        public Job call() throws Exception {
            throw new RuntimeException("no implementat");
        }
        
        // completar
    }
}
