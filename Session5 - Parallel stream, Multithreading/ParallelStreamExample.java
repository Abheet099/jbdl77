import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ParallelStreamExample {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        // Sequential stream
//        numbers.stream().filter(number -> {
//                    System.out.println("In filter method, number: " + number + " thread = " + Thread.currentThread().getName());
//                    return number % 2 == 0;
//                })
//                .map(number -> {
//                    System.out.println("In map method, number: " + number + " thread = " + Thread.currentThread().getName());
//                    return number * number;
//                }).forEachOrdered(number -> {
//                    System.out.println("In for each ordered function, number: " + number + " thread = " + Thread.currentThread().getName());
//                });

        // Parallel stream
        numbers.parallelStream().filter(number -> {
                    System.out.println("In filter method, number: " + number + " thread = " + Thread.currentThread().getName());
                    return number % 2 == 0;
                })
                .map(number -> {
                    System.out.println("In map method, number: " + number + " thread = " + Thread.currentThread().getName());
                    return number * number;
                }).forEachOrdered(number -> {
                    System.out.println("In for each ordered function, number: " + number + " thread = " + Thread.currentThread().getName());
                });
        // Number of cores in system
        System.out.println(Runtime.getRuntime().availableProcessors());

        List<String> list = Arrays.asList("J","A","V","A","H","E","L","L","O","W","O","R","L","D");
        list.stream().forEach(System.out::print);
        System.out.println("");
        list.parallelStream().forEach(System.out::print);

        Optional<Integer> first = numbers.parallelStream().filter(number -> {
            System.out.println("In filter method, number: " + number + " thread = " + Thread.currentThread().getName());
            return number % 2 == 0;
        }).findFirst();
        System.out.println(first);

    }
}
