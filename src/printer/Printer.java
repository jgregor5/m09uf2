package printer;

/**
 *
 * @author julian
 */
public class Printer {
    
    public void printDocument(String username, int pages) {
        
        for (int i=0; i<pages; i++) {
            waitMillis(750); // time to prepare the printing
            printPage(username, i);
        }
    }
    
    private void printPage(String username, int page) {
        
        System.out.println(username + " started  page " + (page + 1));
        waitMillis(750); // time to print it             
        System.out.println(username + " finished page " + (page + 1));
    }
    
    private static void waitMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
