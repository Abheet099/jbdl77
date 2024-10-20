class Parent{
    int a = 10;
    public void displayHello(){
        System.out.println("Hello from Parent!");
    }
    protected void method1(){
        System.out.println("Method1 from parent class");
    }
}

class Child extends Parent{

//    public Child(){
//        super();
//    }
    int a = 20;
    @Override
    protected void method1(){
        super.method1();
        System.out.println("Method1 from child class");
    }
}

public class MethodOverriding {
    public static void main(String[] args) {
        // parent class reference referring to parent class
        Parent parentObj = new Parent();
        parentObj.displayHello();
        parentObj.method1();
        System.out.println("Value of a = "+parentObj.a);

        // child class reference referring to child class
        Child childObj = new Child();
        childObj.displayHello();
        childObj.method1();
        System.out.println("Value of a = "+childObj.a);

        // parent class reference referring to child class
        Parent parentRef = new Child();
        parentRef.displayHello();
        parentRef.method1();
        System.out.println("Value of a = "+parentRef.a);

        // child class reference referring to parent class
//        Child childRef = new Parent();
//        childRef.displayHello();
    }
}
