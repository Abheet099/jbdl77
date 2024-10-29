class Test<T> {
    T obj;

    Test(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return this.obj;
    }
}

public class GenericsExample {
    public static void main(String[] args) {

        // instance of an integer type
        Test<Integer> iObj = new Test<>(15);
        System.out.println(iObj.getObj());

        Test<String> sObj = new Test<>("Java");
        System.out.println(sObj.getObj());
    }

}
