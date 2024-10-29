import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExceptionDemo {
    public static void main(String[] args) throws FileNotFoundException {
        int x = 0;
        try {
            x = 10;
            computeDivision(x);
        }  catch (NullPointerException nullPointerException){
            System.out.println("In Catch block of nullPointerException");
            x = 1;
        } catch (Exception exception) {
            System.out.println("In Catch block of arithmeticException");
            x = x + 1;
        }
        finally {
            System.out.println("In finally block");
        }
        System.out.println("Program executed successfully");

        SingletonDesign.getSingletonObject();

    }

    private static void computeDivision(int i) {
        int x = 100 / i;
        System.out.println("X = " + x);
    }

}

//class childClass extends ExceptionDemo{
//    public static void main() throws FileNotFoundException {
//        FileReader fileReader = new FileReader("/Users/desktop");
//    }
//}
