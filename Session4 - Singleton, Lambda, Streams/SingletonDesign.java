public class SingletonDesign {

    // private instance, so that it can be accessed by only static method
    private static SingletonDesign singletonDesignObj;

    // private constructor;
    private SingletonDesign() {
    }

    public static synchronized SingletonDesign getSingletonObject() {

        if (singletonDesignObj == null) {
            singletonDesignObj = new SingletonDesign();
        }

        return singletonDesignObj;
    }
}
