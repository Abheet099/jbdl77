import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamExample {

    public static void main(String[] args) {
        List<Integer> number = Arrays.asList(1, 2, 3, 4, 5, 6);


        // Filter even number, double them -> result
        List<Integer> annonymousIneerClassList = number.stream().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                System.out.println("In Test method with integer = " + integer);
                return integer % 2 == 0;
            }
        }).map(new Function<Integer, Integer>() {

            @Override
            public Integer apply(Integer integer) {
                System.out.println("In apply method with integer = " + integer);
                return integer * integer;
            }
        }).collect(Collectors.toList());

        System.out.println(annonymousIneerClassList);
        System.out.println("Yes");
        System.out.println(number.stream().filter(i -> i % 2 == 0).map(x -> x * x));

        number.stream().filter(i -> {
                    System.out.println("Inside filter");
                    return i % 2 == 0;
                }
        ).map(x -> x * x);
//        System.out.println(sum);

//        List<Integer> arrayObjectList = number.stream().filter(obj -> obj.isAvailable == true).map(x -> x * x).collect(Collectors.toList());


    }
}
