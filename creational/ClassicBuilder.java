/*
 * Builder Design Pattern
 * - using Classic Builder implementation
 * 
 * What is it?
 * - Build complex objects with different configurations, rather than different types of products
 *   that require an abstract interface for standardisation (Factory Method or Abstract Factory).
 * - Focus on the custom configuration of the same product, not different products or product lines.
 * - Simplify the construction of objects with many different complex configurations.
 * 
 * What problems does it solve?
 * - Complex object creation that has many custom configurations eg cars, pizza, computers, phones
 * - Avoid the use of multiple parameters in object constructors which can be error-prone
 * 
 * Principles
 * 
 * Steps
 * - Product class (no need to abstract as we don't create multiple concrete products, only one!)
 *   This product has many configurations for different parts.
 * - Builder (can be an interface or an abstract class, but usually an interface)
 *   Has build methods to assemble the products
 * - Concrete Builder to specify the specifc configuration
 * - Director (optional) to control the building process
 *   which parts get constructed and the order in which the parts are constructor
 * - Client uses the builder to set the desired parts step-by-step and pass it to the director to start
 *   the build process
 * 
 * Pros
 * - Construct complex objects with many optional components or configurations
 * - Clear separation of construction process from object representation
 * - Allows step-by-step construction
 * - Avoid constructors with multiple parameters which can become unwieldy and error-prone
 * - Common interface for multiple representations of the same object
 *   e.g. same pizza with different toppings
 * - Use when you don't need polymorphic behaviour
 * 
 * Cons
 * - Do not use Builder design pattern when simple object creation suffice
 * - Do not use in performance critical applications as the extra method calls can impact performance
 * - Tight coupling between the builder and the product can reduce flexibility and maintainability
 * - No polymorphic behaviour (it's the same product! But with different configurations!)
 * - Introduces a lot of classes, Builder interface, concrete builders, director
 * - one builder builds one product and is then discarded, sometimes this is desirable eg. state management.
 *   But if you need to build subsequent products you have to create the builder again. So you have to 
 *   consider implementing Factory Method or Abstract Factory for the Builder to return an instance of the
 *   concrete builder class.
 * 
 * Applications
 * 
 */

/*
 * The classic builder implementation is described by the Gang of Four.
 */

class Pizza {
    // Different parts that require configurations
    private String crust;
    private String dough;
    private String sauce;
    private String cheese;
    private String toppings;
    private String size;

    public Pizza() {
        // default configuration
        this.crust = "Unknown";
        this.dough = "Unknown";
        this.sauce = "Unknown";
        this.cheese = "Unknown";
        this.toppings = "Unknown";
        this.size = "Unknown";
    }

    @Override
    public String toString() {
        return "This pizza has " + crust + " crust, " + dough + " dough, " + sauce + " sauce, " + cheese
                + " cheese, " + toppings
                + " toppings, and is " + size + " size.";
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public void setDough(String dough) {
        this.dough = dough;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

interface PizzaBuilder {
    void buildCrust();

    void buildDough();

    void buildSauce();

    void buildCheese();

    void buildToppings();

    void buildSize();

    Pizza getPizza();
}

class PepperoniPizzaBuilder implements PizzaBuilder {
    private Pizza pizza;

    public PepperoniPizzaBuilder() {
        this.pizza = new Pizza();
    }

    @Override
    public void buildCrust() {
        this.pizza.setCrust("Standard");
    }

    @Override
    public void buildDough() {
        this.pizza.setDough("Standard");
    }

    @Override
    public void buildSauce() {
        this.pizza.setSauce("Tomato");
    }

    @Override
    public void buildCheese() {
        this.pizza.setCheese("Mozzarella");
    }

    @Override
    public void buildToppings() {
        this.pizza.setToppings("Pepperoni");
    }

    @Override
    public void buildSize() {
        this.pizza.setSize("Normal");
    }

    @Override
    public Pizza getPizza() {
        return this.pizza;
    }
}

class HawaiianVolcanoPizzaBuilder implements PizzaBuilder {
    private Pizza pizza;

    public HawaiianVolcanoPizzaBuilder() {
        this.pizza = new Pizza();
    }

    // No crust for Hawaiian Volcano Pizza, but we still need to implement it!
    // Some argued it's a flaw, some say it's a trade-off
    // We can look at how other implementation can solve this problem
    // The key is to break down into smaller interfaces
    @Override
    public void buildCrust() {
        this.pizza.setCrust("Thin");
    }

    @Override
    public void buildDough() {
        this.pizza.setDough("Volcano");
    }

    @Override
    public void buildSauce() {
        this.pizza.setSauce("Tomato");
    }

    @Override
    public void buildCheese() {
        this.pizza.setCheese("Mozzarella");
    }

    @Override
    public void buildToppings() {
        this.pizza.setToppings("Chicken Breast, Pineapple Cubes, Chicken Ham");
    }

    @Override
    public void buildSize() {
        this.pizza.setSize("Normal");
    }

    @Override
    public Pizza getPizza() {
        return this.pizza;
    }
}

class PizzaDirector {
    public void constructPepperoniPizza(PizzaBuilder builder) {
        builder.buildCrust();
        builder.buildDough();
        builder.buildSauce();
        builder.buildCheese();
        builder.buildToppings();
        builder.buildSize();
    }

    public void constructHawaiianVolcanoPizza(PizzaBuilder builder) {
        // no crust to build, can you see you may not
        // Director make fewer calls
        builder.buildDough();
        builder.buildSauce();
        builder.buildCheese();
        builder.buildToppings();
        builder.buildSize();
    }
}

class ClassicBuilder {
    public static void main(String[] args) {
        PizzaDirector director = new PizzaDirector();
        PepperoniPizzaBuilder pepperoniBuilder = new PepperoniPizzaBuilder();
        HawaiianVolcanoPizzaBuilder hawaiianBuilder = new HawaiianVolcanoPizzaBuilder();

        director.constructPepperoniPizza(pepperoniBuilder);
        director.constructHawaiianVolcanoPizza(hawaiianBuilder);

        Pizza pepperoniPizza = pepperoniBuilder.getPizza();
        Pizza hawaiianVolcanoPizza = hawaiianBuilder.getPizza();
        System.out.println(pepperoniPizza);
        System.out.println(hawaiianVolcanoPizza);
    }
}