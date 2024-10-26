public class AgeNotValidExceptionExample {
    public static void main(String[] args) throws AgeNotValidException {

        try{
            //business validation
            throw new AgeNotValidException("User doesn't meet the age criteria");
        } catch (AgeNotValidException ageNotValidException){
            System.out.println("In catch block");
        }
        System.out.println("Program executed successfully");
    }
}

class AgeNotValidException extends Exception {
    public AgeNotValidException(String s) {
        super(s);
    }
}

