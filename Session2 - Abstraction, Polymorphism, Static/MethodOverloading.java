public class MethodOverloading {

    public double sum(double a, double b){
        return (a+b);
    }

    public int sum(int a, int b, int c){
        return (a+b+c);
    }

    public static void main(String[] args) {
        MethodOverloading methodOverloading = new MethodOverloading();
        System.out.println(methodOverloading.sum(10,20));
        System.out.println(methodOverloading.sum(10.0,20.0));
    }
}
