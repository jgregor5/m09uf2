package basic;

/**
 *
 * @author julian
 */
public class ThreadKindTest {
    
    private static void dormir(int secs, String message) {
        try {
            System.out.println("inici " + message);
            Thread.sleep(secs*1000);
            System.out.println("final " + message);
        } catch (InterruptedException ex) {
            System.out.println("interrupció");
        }
    }
    
    public static void main(String[] args) {
        
        new MySubclass().start();
        new Thread(new MyRunnable()).start();
        dormir(5, "main");
    }
    
    private static class MySubclass extends Thread {
        
        @Override
        public void run() {
            dormir(10, "MySubclass");
        }        
    }
    
    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            dormir(10, "MyRunnable");
        }
    }
}
