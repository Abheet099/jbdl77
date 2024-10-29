import java.util.function.Predicate;

@FunctionalInterface
interface DoSumInterface {
    public int getSum(int a, int b);

    default void printString() {
        System.out.println("I am default method");
    }
}

public class FunctionalInterfaceExample {

    public static void main(String[] args) {
        DoSumInterface doSumInterface = new DoSumInterface() {
            @Override
            public int getSum(int a, int b) {
                return a + b;
            }
        };

        DoSumInterface doSumInterface2 = new DoSumInterface() {
            @Override
            public int getSum(int a, int b) {
                return a + 1 + b;
            }
        };


        System.out.println(doSumInterface);
        System.out.println(doSumInterface.getSum(5, 10));
        doSumInterface.printString();

//        (Argument list) -> {Body}
        // ClassName::methodName - method reference
        DoSumInterface lambdaExpression =  (a,b) -> {return (a+b);};
        System.out.println(lambdaExpression.getSum(5, 10));

    }

}
