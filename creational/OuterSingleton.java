/*
 * This implementation is specific to Java because Java has a inner class concept.
 * 
 * Use Class Loading Concept:
 * - Classes are only loaded one time in memory by JDK.
 * - Inner classes are only loaded when they come into scope, ie, when it is being used.
 * - Usually for Lazy Loading only, because traditional implementation of Lazy Loading
 *   isn't thread-safe and you need to use synchronized on the global access method or 
 *   a synchronized block
 * - Inner class provides an elegant way to implement thread-safe Lazy Loading
 * - for Eager Loading, look at EagerSingleton.java which uses static initialization upon class loading
 */
public class OuterSingleton {
    private OuterSingleton() {
        System.out.println("Creating an OuterSingleton instance");
    }

    public static OuterSingleton getInstance() {
        // Lazy Loading, because the first time getInstance() is called, then new OuterSingleton()
        // will be called, because this is the first time the inner class will be accessed
        // and it will only be loaded once
        // and INSTANCE being a static member, this will also be called only once.
        return InnerSingleton.INSTANCE;
    }

    // private inner class
    private static class InnerSingleton {
        // JVM guarantees thread-safety for static initialization
        private static final OuterSingleton INSTANCE = new OuterSingleton();
    }
}
