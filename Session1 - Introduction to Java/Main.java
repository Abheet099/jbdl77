public class Main{

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Create Object - Using new keyword
        Dog dog = new Dog("Tom");
        System.out.println(dog);
        System.out.println(dog.name);
        dog.bark();

        // using class.forName
        Dog dog2 = (Dog) Class.forName("Dog").newInstance();
        System.out.println(dog2);
        System.out.println(dog2.name);
        dog2.eat();

        Dog dog3 = dog;
        System.out.println(dog3);
        System.out.println(dog3.name);

        System.out.println(dog == dog3);


        // using clone method
        Dog dog4 = dog.clone();
        System.out.println(dog4);
        System.out.println(dog4.name);
        System.out.println(dog == dog4);

//        Deep Copy: Create new object with its own copies
//        Shallow Copy: Create a new object that references same elements as original

        int a = 10;
        System.out.println(a);
        System.out.println(dog.name);
        System.out.println(Dog.breed);

    }
}
