public class InterfaceExample {

    public static void main(String[] args) {
        ProgrammingLanguage language = new ProgrammingLanguage();
        language.getName("Java");
        System.out.println(language.getNumberOfCharachters());
        language.printName();
    }
}


interface Language{
    void getName(String name);
    int getNumberOfCharachters();
    int getNumberOfWords();

    default void printName(){
        System.out.println("I am language");
    }

    int count = 0;
}

interface Parser{
    void getName(String name);

    default void printName(){
        System.out.println("I am parser");
    }
}

class ProgrammingLanguage implements Language, Parser{

    @Override
    public void getName(String name) {
        System.out.println(name);
    }

    @Override
    public int getNumberOfCharachters() {
        return 1500;
    }

    @Override
    public int getNumberOfWords() {
        return 0;
    }

    @Override
    public void printName() {
        Parser.super.printName();
    }
}

