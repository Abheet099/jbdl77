class Animal{
    String name;
    int age;

    public void eat(){
        System.out.println("I am eating");
    }
    public void run(){
        System.out.println("I am running");
    }
}

class Cat extends Animal{
}

class Horse extends Animal{

}

public class InheritanceExample {

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.eat();

        Horse horse = new Horse();
        horse.run();
    }
}
