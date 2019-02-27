package basic;

/**
 *
 * @author julian
 */
public class ThreadStopTest {
    
    public static void main(String[] args) {
        
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        try {
            Thread.sleep(10L * 1000L);
        } catch (InterruptedException ex) {
            System.out.println("interrupció");
        }

        myRunnable.doStop();        
    }

    public static class MyRunnable implements Runnable {

        private boolean doStop = false;

        public synchronized void doStop() {
            this.doStop = true;
        }

        private synchronized boolean keepRunning() {
            return this.doStop == false;
        }

        @Override
        public void run() {
            while (keepRunning()) {
                // keep doing what this thread should do.
                System.out.println("executant");

                try {
                    Thread.sleep(3L * 1000L);
                } catch (InterruptedException ex) {
                    System.out.println("interrupció");
                }

            }
        }
    }

}
