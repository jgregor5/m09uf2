package printer;

/**
 *
 * @author julian
 */
public class AsyncPrinter implements Runnable {
    
    // use this printer to print!
    private final Printer printer;
    
    public AsyncPrinter() {
        this.printer = new Printer();
        // completar
    }
    
    public Job printDocument(String username, int pages) {
        throw new RuntimeException("no implementat");
    }
    
    public void waitForJob(Job job) {
        throw new RuntimeException("no implementat");
    }
    
    public void stop() {
        throw new RuntimeException("no implementat");
    }

    @Override
    public void run() {
        throw new RuntimeException("no implementat");
    }
    
    static class Job {
        
        public Job(int id, String username, int pages) {
            // completar
        }
    }
}
