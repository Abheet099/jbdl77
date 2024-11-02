import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FactorialMT extends Thread {
    BigInteger result;
    int number;

    FactorialMT(int number) {
        this.number = number;
    }

    public static void main(String[] args) {
        Integer[] numbers = {10000, 20000, 50000, 30000, 43000, 65000, 42000};
        Integer[] shortNumbers = {1, 2, 3, 4, 5, 6, 7, 8};

        long start = System.currentTimeMillis();
        List<FactorialMT> threads = Arrays.stream(numbers)
                .map(number -> {
                    FactorialMT thread = new FactorialMT(number);
                    thread.start();
                    return thread;
                }).collect(Collectors.toList());

        threads.stream().forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Input = " + thread.number + ", output = " + thread.result);
        });

        long end = System.currentTimeMillis();
        System.out.println("The total time is: " + (end - start) + " millis");
    }

    public void run() {
        this.result = calculateFactorial();
        System.out.println(result);
    }

    public BigInteger calculateFactorial() {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= this.number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }


}
