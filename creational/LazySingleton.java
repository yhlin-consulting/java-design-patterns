
/*
Singleton Design Pattern

What is it:
- Ensure that a class has only one instance and provide a global access interface to it

What problem can it solve?
- Logging
- Database connections
- Configurations need to be accessed by different parts of the application
- Managing thread pools
- Message dispatchers (my experience)
- Centrally manage state and actions of user interface components
- Document printing: organise and streamline print jobs

Principles
- Single Instance, Global Access
- Lazy or Eager Initialization
- Thread Safety: prevent multiple threads from creating separate instances simultaneously
- Make constructor private to prevent direct instantiation

Steps
- Private static member
- Private constructor
- Static Factory Method

Pros
- Guarantees only one instance with a unique identifier, prevents naming issues
- Flexible, supports Lazy and Eager Initialization
- Can be thread-safe with the proper implementation
- Lower memory usage in applications where memory resources are limited

Cons
- Can make unit testing difficult since it introduces a global state
- If later on multiple instances are needed, significant code changes are required
- Subclassing a singleton is tricky because the constructor is private;
   may break standard inheritance practices
*/
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

class LazySingleton {
    private static LazySingleton instance;

    // private constructor
    private LazySingleton() {
        System.out.println("Singleton is created");
    }

    // public global static factory method for global access
    public static synchronized LazySingleton getInstance() {
        //synchronized ensures only one thread can execute this at a time
        //cons: using synchronized is expensive and this is called everytime getInstance() is caleld
        //may impact performance, so use this if performance is not critical
        if (instance == null) {
            // Lazy Instantiation
            instance = new LazySingleton();
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("Singleton instance calling instance method to do something.");
    }
}

class LazySingletonMain {
    public static void main(String[] args) {
        // call getInstance() first time
        LazySingleton.getInstance().doSomething();

        // new Singleton(); // can't do this!
        // error: Singleton() has private access in Singleton
        // it is sayinng the constructor Singleton() is private

        // call getInstance() second time
        LazySingleton.getInstance().doSomething(); // the constructor is not called again

        // Standard Output
        /*
         * Singleton is created
         * Singleton instance calling instance method to do something.
         * Singleton instance calling instance method to do something.
         */

        // Singleton class should be thread-safe.
        // It should not allow multiple threads to each create a singleton instance
        // causing multiple instances to be created
        int numThreads = 5000;
        Set<LazySingleton> instances = Collections.synchronizedSet(new HashSet<>());
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        System.out.println("Starting thread-safety test for Singleton");
        for (int i = 0; i < numThreads; i++) {
            executor.submit(() -> {
                instances.add(LazySingleton.getInstance());
            });
        }
        try {
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);

            // verify the number of unique instances
            if (instances.size() == 1) {
                System.out.println("SUCCESS: only one instance of Singleton is created");
            } else {
                System.err.println("FAILURE: " + instances.size() + " instances of Singleton were created.");
                System.err.println("This indicates a thread-safety issue in your Singleton implementation.");
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }

    }
}