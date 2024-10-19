public class Dog implements Cloneable{

    public Dog(){
    }
    public Dog(String name){
        this.name = name;
    }

    public Dog(String name, int age){
        this(name);
        this.age = age;
    }

    public String name;
    //    private String breed;
    int age;
    protected String color;
    public static String breed;

    public void bark(){
        System.out.println("I am barking!");
    }
    public void eat(){
        System.out.println("I am eating");
    }


    @Override
    public Dog clone() {
        try {
            Dog clone = (Dog) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
//
//    public static void main(String[] args) {
//        Lion dog = new Lion();
//        dog.age = 10;
//    }
}
