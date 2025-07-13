/*
 * Builder Design Pattern
 * - using Fluent Builder implementation
 * 
 * What is Fluent Builder Implementation?
 * - Method Chaining
 * - Each builder method returns the builder itself, allowing for method chaining
 * - Do away with Director, resulting in a more readable and expressive code
 * - Makes use of static nested builder class, and the builder now sets the default values,
 *   not the product itself
 * - Very open and flexible implementation, no concrete builders to define
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

    public static class PizzaBuilder {
        private String crust;
        private String dough;
        private String sauce;
        private String cheese;
        private String toppings;
        private String size;

        public PizzaBuilder() {
            // default configuration
            this.crust = "Unknown";
            this.dough = "Unknown";
            this.sauce = "Unknown";
            this.cheese = "Unknown";
            this.toppings = "Unknown";
            this.size = "Unknown";
        }

        public PizzaBuilder withCrust(String crust) {
            this.crust = crust;
            return this;
        }

        public PizzaBuilder withDough(String dough) {
            this.dough = dough;
            return this;
        }

        public PizzaBuilder withSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public PizzaBuilder withCheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public PizzaBuilder withToppings(String toppings) {
            this.toppings = toppings;
            return this;
        }

        public PizzaBuilder withSize(String size) {
            this.size = size;
            return this;
        }

        public Pizza build() {
            // Validation before building
            if (this.dough == null || this.sauce == null || this.size == null) {
                throw new IllegalStateException("The pizza must have a dough, sauce and size.");
            }
            return new Pizza(this);
        }
    }
}

public class FluentBuilder {
    public static void main(String[] args) {
        Pizza pepperoniPizza = new Pizza.PizzaBuilder()
                .withCrust("Standard")
                .withDough("Standard")
                .withSauce("Tomato")
                .withCheese("Mozzarella")
                .withToppings("Pepperoni")
                .withSize("Normal")
                .build();
        Pizza hawaiianVolcanoPizza = new Pizza.PizzaBuilder()
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
