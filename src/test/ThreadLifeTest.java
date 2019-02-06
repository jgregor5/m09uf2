
package test;

/**
 *
 * @author julian
 */
public class ThreadLifeTest implements Runnable {

    @Override
    public void run() {
        
        try {
            System.out.println("a dormir...");
            Thread.sleep(5000);
            System.out.println("despert!");
            
        } catch (InterruptedException ex) {
            System.out.println("interrupció");
        }
    }
    
    public static void main(String[] args) {
        
        ThreadLifeTest test = new ThreadLifeTest();
        
        Thread thread = new Thread(test, "fill");
        thread.start();

        System.out.println("acaba el main");
    }
}
