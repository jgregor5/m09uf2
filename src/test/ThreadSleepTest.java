package test;

/**
 * user vs daemon threads:
 * 
 * The difference between these two types of threads is that if the JVM 
 * determines that the only threads running in an application are daemon 
 * threads (i.e., there are no user threads), the Java runtime closes down the 
 * application. On the other hand, if at least one user thread is alive, 
 * the Java runtime won't terminate your application. 
 *
 * @author julian
 */
public class ThreadSleepTest implements Runnable {
    
    private static long millis;
    private int secs;
    
    public ThreadSleepTest(int secs) {
        this.secs = secs;
    }
    
    @Override
    public void run() {
        System.out.println(since() + "> inici del fill");
        sleep(this.secs);
        System.out.println(since() + "< fi del fill");
    }
    
    // UTILS

    private static void sleep(long seconds) {
        
        System.out.println(since() + "> dorm " + Thread.currentThread().getName());
        
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            System.out.println(since() + "* interrupcio de " + Thread.currentThread().getName());
        }
        
        System.out.println(since() + "< desperta " + Thread.currentThread().getName());
    }
    
    private static String since() {
        return String.format("%6d: ", System.currentTimeMillis() - millis);
    }
    
    // TEST
        
    public static void test(boolean fillDaemon, int secsPare, int secsFill) {
        
        System.out.println(since() + String.format("* daemon:%b main:%ds fill: %ds", fillDaemon, secsPare, secsFill));
        Thread thread = new Thread(new ThreadSleepTest(secsFill), "fill");
        thread.setDaemon(fillDaemon);
        thread.start();
        sleep(secsPare);
    }    
    
    public static void main(String args[]) {
        
        millis = System.currentTimeMillis();
        System.out.println(since() + "> inici del main");        
        
        //test(false, 10, 5);
        //test(false, 5, 10);        
        //test(true, 10, 5);
        test(true, 5, 10);
        
        System.out.println(since() + "< fi del main");
    }    
}
