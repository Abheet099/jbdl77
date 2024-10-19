package Package2;

import Package1.CarnivorousAnimal;

public class Lion extends CarnivorousAnimal {
    public static void main(String[] args) {
        CarnivorousAnimal animal = new CarnivorousAnimal();
        animal.name = "";
        animal.age = 10;

        Lion lion = new Lion();
        lion.age = 10;

    }
}
