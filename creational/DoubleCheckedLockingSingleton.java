/*
 * Problem with using synchronized on the public global access factory method is:
 * it is no longer need once the Singleton object is created
 * but it will continue to be enforced everytime it is being called
 * potentially causing a decrease in performance
 * 
 * We can use Double Checked Locking mechanism:
 * - use volatile on the private static instance to ensure visibility
 * - use synchronized on only the code block that creates the instance,
 *   but not on the entire getInstance() method to reduce overhead,
 *   and at the same time still ensuring thread-safety
 * 
 */


public class DoubleCheckedLockingSingleton {
    // volatile ensures object is stored in main memory, not in a thread's local cache
    // so that when the instance is created, it will be visible to all threads
    // volatile is about visibility
    private static volatile DoubleCheckedLockingSingleton instance = null;
    private DoubleCheckedLockingSingleton() {
        System.out.println("Double Checked Locking Singleton is being created.");
    }
    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            // this is a class level lock, a lock on the entire class
            // here, DoubleCheckedLockingSingleton.class is the monitor object, or lock object
            // because getInstance() is a class method
            // also ensure no other threads can enter ANY other synchronized blocks in this class
            synchronized(DoubleCheckedLockingSingleton.class) {
                System.out.println("Entering synchronized block");
                if (instance == null) { // we have to again because
                                        //multiple threads can be checking on top
                    instance = new DoubleCheckedLockingSingleton();
                    System.out.println("Exiting synchronized block");
                }
            }
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("DoubleCheckedLockingSingleton instance is doing something.");
    }
}

class DoubleCheckedLockingSingletonMain {
    public static void main(String[] args) {
        DoubleCheckedLockingSingleton.getInstance().doSomething();
        DoubleCheckedLockingSingleton.getInstance().doSomething();
    }
}
