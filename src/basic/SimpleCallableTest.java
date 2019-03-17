package basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author julian
 */
public class SimpleCallableTest {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<String> f1 = executor.submit(new ToUpperCallable("hello"));
        Future<String> f2 = executor.submit(new ToUpperCallable("world"));

        try {
            long millis = System.currentTimeMillis();
            System.out.println("main " + f1.get() + " " + f2.get() + 
                    " in millis: " + (System.currentTimeMillis() - millis));            
            
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        
        executor.shutdown();
    }

    private static final class ToUpperCallable implements Callable<String> {

        private String word;
        
        public ToUpperCallable(String word) {
            this.word = word;
        }
        
        @Override
        public String call() throws Exception {
            String name = Thread.currentThread().getName();            
            System.out.println(name + " calling for " + word);
            Thread.sleep(2500);
            String result = word.toUpperCase();
            System.out.println(name + " result " + word + " => " + result);
            return result;
        }
    }
}
