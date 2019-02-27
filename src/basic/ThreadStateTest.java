package basic;

/**
 *
 * @author julian
 */
public class ThreadStateTest {

    public static void main(String args[]) {

        Thread thread = new Thread(new BasicThread(), "fill");
        System.out.println("estat: " + thread.getState());
        
        thread.start();
        System.out.println("estat: " + thread.getState());
        
        try {
            boolean keepRunning = true;
            int count = 1;
            
            while (keepRunning) {
                Thread.sleep(2000);
                System.out.println(count + " estat: " + thread.getState());
                count++;
                if (count == 4) {
                    //6 seconds elapsed
                    synchronized (thread) {
                        thread.notify();
                    }
                }
                if (Thread.State.TERMINATED == thread.getState()) {
                    keepRunning = false;
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static class BasicThread implements Runnable {

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            try {
                //Making the thread sleep for 5 seconds
                System.out.println("el fil dorm 5 segons");
                thread.sleep(5000);
                System.out.println("el fil espera");
                synchronized (thread) {
                    thread.wait();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}