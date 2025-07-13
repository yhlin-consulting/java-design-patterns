/*
 * Builder Design Pattern
 * - using Step Builder Implementation
 * 
 * What is Step Builder Implementation?
 * - Helps to resolve the issue with Classic Builder implementation by breaking one large interface
 *   into smaller interfaces
 * 
 * Pros
 * - Enforces the steps in building a product
 * 
 * Cons
 * - Too many interfaces
 * - Can be too rigid
 */


 class Pizza {
    // Different parts that require configurations
    private String crust;
    private String dough;
    private String sauce;
    private String cheese;
    private String toppings;
    private String size;

    // private constructor to force the use of builder
    private Pizza(PizzaBuilder builder) {
        this.crust = builder.crust;
        this.dough = builder.dough;
        this.sauce = builder.sauce;
        this.cheese = builder.cheese;
        this.toppings = builder.toppings;
        this.size = builder.size;
    }

    @Override
    public String toString() {
        return "This pizza has " + crust + " crust, " + dough + " dough, " + sauce + " sauce, " + cheese
                + " cheese, " + toppings
                + " toppings, and is " + size + " size.";
    }

    // Step Interfaces
    public interface IBuildCrust {
        IBuildDough withCrust(String crust);
    }

    public interface IBuildDough {
        IBuildSauce withDough(String dough);
    }

    public interface IBuildSauce {
        IBuildCheese withSauce(String sauce);
    }

    public interface IBuildCheese {
        IBuildToppings withCheese(String cheese);
    }

    public interface IBuildToppings {
        IBuildSize withToppings(String toppings);
    }

    public interface IBuildSize {
        IBuild withSize(String size);
    }

    public interface IBuild {
        Pizza build();
    }

    public static class PizzaBuilder
            implements IBuildCrust, IBuildDough, IBuildSauce, IBuildCheese, IBuildToppings, IBuildSize, IBuild {
        private String crust;
        private String dough;
        private String sauce;
        private String cheese;
        private String toppings;
        private String size;

        private PizzaBuilder() { // private to force using startBuilder()
            // default configuration
            this.crust = "Unknown";
            this.dough = "Unknown";
            this.sauce = "Unknown";
            this.cheese = "Unknown";
            this.toppings = "Unknown";
            this.size = "Unknown";
        }

        public static PizzaBuilder startBuilder() {
            return new PizzaBuilder();
        }

        @Override
        public IBuildDough withCrust(String crust) {
            this.crust = crust;
            return this;
        }

        @Override
        public IBuildSauce withDough(String dough) {
            this.dough = dough;
            return this;
        }

        public IBuildCheese withSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        @Override
        public IBuildToppings withCheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        @Override
        public IBuildSize withToppings(String toppings) {
            this.toppings = toppings;
            return this;
        }

        @Override
        public IBuild withSize(String size) {
            this.size = size;
            return this;
        }

        @Override
        public Pizza build() {
            return new Pizza(this);
        }
    }
}

public class StepBuilder {
    public static void main(String[] args) {
        Pizza pepperoniPizza = Pizza.PizzaBuilder.startBuilder()
                .withCrust("Standard")
                .withDough("Standard")
                .withSauce("Tomato")
                .withCheese("Mozzarella")
                .withToppings("Pepperoni")
                .withSize("Normal")
                .build();
        Pizza hawaiianVolcanoPizza = Pizza.PizzaBuilder.startBuilder()
                .withDough("Volcano")
                .withSauce("Tomato")
                .withCheese("Mozzarella")
                .withToppings("Chicken Breast, Pineapple Cubes, Chicken Ham")
                .withSize("Normal")
                .build();

        System.out.println(pepperoniPizza);
        System.out.println(hawaiianVolcanoPizza);
    }
}