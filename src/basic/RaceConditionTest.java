package basic;

/**
 *
 * @author julian
 */
public class RaceConditionTest {
    
    private static final int NUMBER = 1000;

    private static class Comptador {

        protected long count = 0;

        // synchronize this to solve the race condition!
        public void add(long value) {       
            
            long nextCount = this.count + value;
            esperaUnaMica();
            this.count = nextCount;
        }
    }
    
    private static void esperaUnaMica() {
        Integer temps = 500;
        while (temps > 0) { temps--; }
    }
    
    private static class Modificador implements Runnable {

        private int amount;
        private Comptador counter;
        
        private Modificador(Comptador counter, int amount) {
            this.counter = counter;
            this.amount = amount;
        }
        
        @Override
        public void run() {            
            this.counter.add(amount);
        }        
    }
    
    public static void main(String[] args) {
        
        // calculate all
        int sum = 0;
        for (int i=0; i<NUMBER; i++) {
            sum += i;
        }
        System.out.println("sum is " + sum);
        
        Comptador counter = new Comptador();
        Thread[] threads = new Thread[NUMBER];        
        
        // start all
        for (int i=0; i<NUMBER; i++) {
            threads[i] = new Thread(new Modificador(counter, i));
            threads[i].start();
        }
        
        // join all
        for (int i=0; i<NUMBER; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        System.out.println("counter is " + counter.count);
    }
}
