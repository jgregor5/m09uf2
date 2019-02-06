
package test;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author julian
 */
public class TimerTest {
    
    // TASKS
    
    public static class MyTimerTaskOne extends TimerTask {
        @Override
        public void run() {
            System.out.println("ONE");
        }        
    }

    public static class MyTimerTaskTwo extends TimerTask {
        @Override
        public void run() {
            System.out.println("TWO");
        }        
    }

    public static class MyTimerTaskThree extends TimerTask {
        @Override
        public void run() {
            System.out.println("THREE");
            timer.cancel();
        }        
    }
    
    private static Timer timer;
    
    public static void main(String[] args) {
        
        timer = new Timer();
        
        // un cop, als 5 segons
        timer.schedule(new MyTimerTaskOne(), 5000);
        // cada segon a partir del mig segon
        timer.schedule(new MyTimerTaskTwo(), 500, 1000);
        // als 10 segons, i cancela
        timer.schedule(new MyTimerTaskThree(), 10000);
        
        System.out.println("acaba el main");
    }
}
