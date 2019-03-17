package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author julian
 */
public class CallableFutureTest {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<Future<Integer>> resultList = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            Integer number = random.nextInt(10);
            FactorialCalculator calculator = new FactorialCalculator(number);
            Future<Integer> result = executor.submit(calculator);
            resultList.add(result);
        }

        for (Future<Integer> future : resultList) {
            try {
                System.out.println(Thread.currentThread().getName() + 
                        " the result is " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        
        executor.shutdown();
    }

    static class FactorialCalculator implements Callable<Integer> {

        private Integer number;

        public FactorialCalculator(Integer number) {
            this.number = number;
        }

        @Override
        public Integer call() throws Exception {
            int result = 1;
            if ((number == 0) || (number == 1)) {
                result = 1;
            } else {
                for (int i = 2; i <= number; i++) {
                    result *= i;
                    TimeUnit.MILLISECONDS.sleep(20);
                }
            }
            System.out.println(Thread.currentThread().getName() + 
                    " result for " + number + " is " + result);
            return result;
        }
    }
}
