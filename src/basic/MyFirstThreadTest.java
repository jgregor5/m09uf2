package basic;

/**
 *
 * @author julian
 */
public class MyFirstThreadTest implements Runnable {

    @Override
    public void run() {
        System.out.println("executant " + Thread.currentThread().getName());
    }
    
    public static void main(String[] args) {
        
        Runnable myRunnable = new MyFirstThreadTest();
        Thread thread = new Thread(myRunnable, "fill");
        thread.start();
        System.out.println("acabant " + Thread.currentThread().getName());
    }
}
