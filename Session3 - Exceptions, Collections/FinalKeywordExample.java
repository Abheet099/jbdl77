public class FinalKeywordExample {

    public static void main(String[] args) {

        final int a;
        a = 10;
//        a=20;
        System.out.println(a);

        final FinalKeywordExample finalKeywordExampleObj = new FinalKeywordExample();
//        finalKeywordExampleObj = new FinalKeywordExample();

        finalKeywordExampleObj.hashCode();
        // primitive type int
        int c = 10;
        // Integer object class
        Integer b = 10;
        System.out.println(b);
    }

    final void finalMethod() {
    }

    ;

}

final class childClass extends FinalKeywordExample {
    // override ia not possible
//    void finalMethod(){};
}


