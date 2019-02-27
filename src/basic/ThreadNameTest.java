package basic;

/**
 *
 * @author julian
 */
public class ThreadNameTest implements Runnable {

    @Override
    public void run() {
        System.out.println("em dic " + Thread.currentThread().getName());
    }
    
    public static void main(String[] args) {
        
        ThreadNameTest test = new ThreadNameTest();
        test.run();        
        
        Thread thread = new Thread(test, "fill");
        thread.start();        
    }
}
