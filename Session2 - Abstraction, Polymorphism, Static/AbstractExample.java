public class AbstractExample {
}

abstract class Bank{

    abstract int getRateOfInterest();
    abstract void withdraw(int amount);
    abstract void deposit(String accountNumber, int amount);

    public void openAccount(){
        System.out.println("I am opening an account");
    }
}

class SBI extends Bank{

    @Override
    int getRateOfInterest() {
        return 7;
    }

    @Override
    void withdraw(int amount) {

    }

    @Override
    void deposit(String accountNumber, int amount) {

    }

    public void openAccount(){
        System.out.println("I am opening an SBI account");
    }
}

class PNB extends Bank{

    @Override
    int getRateOfInterest() {
        return 8;
    }

    @Override
    void withdraw(int amount) {

    }

    @Override
    void deposit(String accountNumber, int amount) {

    }
}
