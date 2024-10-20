public class FactoryDesignPattern {

    public static void main(String[] args) {
        AtmFactory atmFactory = new AtmFactory();

        // read input from card
        String inputCard = "SBI";
        ATM atm = atmFactory.getAtmObject(inputCard);
        atm.withdraw();
    }
}


interface ATM{
    void withdraw();
    void deposit();
    void checkBalance();
}

class SBIAtm implements ATM{
    @Override
    public void withdraw() {
    }
    @Override
    public void deposit() {
    }
    @Override
    public void checkBalance() {
    }
}

class PNBAtm implements ATM{
    @Override
    public void withdraw() {
    }
    @Override
    public void deposit() {
    }
    @Override
    public void checkBalance() {
    }
}

class AtmFactory{
    public ATM getAtmObject(String cardBankName){
        if(cardBankName.equalsIgnoreCase("SBI")){
            return new SBIAtm();
        } else if (cardBankName.equalsIgnoreCase("PNB")) {
            return new PNBAtm();
        } else{
            return null;
        }
    }
}