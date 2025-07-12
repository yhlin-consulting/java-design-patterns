/*
 * Read more about Singleton design pattern in LazySingleton.java
 */
public class EagerSingleton {
    //eager initialization - instance is created upon class loading
    //guaranteed to be thread-safe, because JVM handles synchronization for static initialization
    //BUT use this when the Singleton object is light
    private static EagerSingleton instance = new EagerSingleton();

    //private constructor
    private EagerSingleton() {
        System.out.println("EagerSingleton instance is created.");
    }

    //public global access factory method
    public static EagerSingleton getInstance() {
        return instance;
    }

    public void doSomething() {
        System.out.println("EagerSingleton instance is doing something.");
    }
}

class EagerSingletonMain {
    public static void main(String[] args) {
        EagerSingleton.getInstance().doSomething();
        EagerSingleton.getInstance().doSomething();
    }
}

