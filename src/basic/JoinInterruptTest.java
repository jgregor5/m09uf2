package basic;

/**
 *
 * @author julian
 */
public class JoinInterruptTest {
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("> testJoin");
        testJoin();
        System.out.println("> testInterrupt");
        testInterrupt();
    }
    
    private static void testInterrupt() {
        
        Thread thread = new Thread(new MyRunnable(5));
        thread.start();
        
        try {
            Thread.sleep(3000);
            thread.interrupt();
            
        } catch (InterruptedException ex) {
            System.out.println("interrupció");
        }
    }
    
    private static void testJoin() {
        
        Thread thread = new Thread(new MyRunnable(5));
        thread.start();
        
        try {
            System.out.println("joining...");
            thread.join();
            System.out.println("joined!");
            
        } catch (InterruptedException ex) {
            System.out.println("");
        }
    }
    
    private static class MyRunnable implements Runnable {
        
        private int count;
        
        public MyRunnable(int count) {
            this.count = count;
        }

        @Override
        public void run() {            
            while (--count > 0) {
                try {
                    System.out.println("executant");
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    System.out.println("interrupció al runnable");
                    return;
                }
            }
        }
        
    }
}
