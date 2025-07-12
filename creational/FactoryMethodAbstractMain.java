/*
 * Factory Method Design Pattern
 * 
 * What is it?
 * - Defines an interface (Java interface or abstract class) for creating an object
 * - let the concrete subclass decide which class to instantiate (defer instantiation to a subclass)
 * 
 * What problems can it solve?
 * - Avoid tight coupling between client and products
 *   e.g. instead of creating the product directly (using strings, if else), it should just
 *   import a factory and call it to make a product
 * - Avoid changing core business logic which can introduce new bugs when new products are made 
 * 
 * Principles
 * - Concept: factories are supposed to be experts in making products
 * - Hide object creation logic from client, who does not have expertise in building the 
 * - Adheres to SOLID Principles:
 *   - Single Responsibility Principle (SRP): one factory, one type of product
 *   - Open/Closed Principle (OCP): classes, modules functions should be open for extension, 
 *                                  not modification
 *   - Liskov Substitution Principle (LSP): objects of superclass should be replaceable with objects of
 *                                          subclasses without affecting correctness
 *   - Interface Segregation Principle (ISP): clients should not be forced to depend on interface 
 *                                            they do not use, break large interfaces into smaller ones
 *   - Dependency Inversion Principle (DIP): Abstractions should not depend on details. Details should
 *                                           not depend on abstractions.
 * 
 * Steps
 * - Define an interface or abstract class for the product
 * - Define concrete subclasses for several specific products
 * - Define an interface or abstract class for the factory
 * - Define concrete subclasses for several specific factories
 * - Use the factories to make the products instead of making the products myself
 * 
 * Pros
 * - separate object creation from client, allowing more flexibility and maintainability
 * - new product types can be easily added without altering client code (risk introducing bugs)
 * - simplifies unit testing by allowing mock production creation
 * - hides product classes from clients, reduce dependencies
 * 
 * Cons
 * - adding more classes and interfaces can complicate maintenance
 * - polymorphism and dynamic binding introduce a little performance overhead
 * - concrete creators are tightly coupled to concrete products 
 *   (anyway this is not the problem this pattern is solving, it's solving client issues)
 * - avoid this pattern if simple object creation suffice
 * - testing factories are more complicated than simpler designs
 * 
 * Applications
 * - JDBC uses factories to make connections
 * - Frameworks like Spring use factories to manage beans
 * - Swing and JavaFX uses factories to product UI components
 * - Tools like log4j use factories to create loggers with various configurations
 * - Serialization frameworks use factories to generate objects from serialized data
 */

/*
 * Extensibility and Flexibility:
 *    for this design pattern, each of the abstract and concrete classes can be in individual files
 *    hence when there are new products, none of the existing files need to be modified
 *    you just have to define new products and factories in new files
 *    this makes the design more flexible and extensible and less error prone as the core logic remains
 */
abstract class Product {
    public abstract void doSomething();
}

class ProductA extends Product {
    @Override
    public void doSomething() {
        System.out.println("Product A is doing something");
    }
}

class ProductB extends Product {
    @Override
    public void doSomething() {
        System.out.println("Product B is doing something");
    }
}

abstract class Factory {
    public abstract Product createProduct();
}

class ProductAFactory extends Factory {
    @Override
    public Product createProduct() {
        return new ProductA();
    }
}

class ProductBFactory extends Factory {
    @Override
    public Product createProduct() {
        return new ProductB();
    }
}

class FactoryMethodAbstractMain {
    public static void main(String[] args) {
        // Client
        Factory factoryA = new ProductAFactory();
        Product productA = factoryA.createProduct();
        Factory factoryB = new ProductBFactory();
        Product productB = factoryB.createProduct();

        productA.doSomething();
        productB.doSomething();
    }
}