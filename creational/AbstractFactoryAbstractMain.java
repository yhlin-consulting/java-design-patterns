/*
 * Abstract Factory Design Pattern
 * 
 * What is it?
 * This is very similar to Factory Method design pattern. It is just another higher level of abstraction
 * to be used in more complex creation scenarios. It is used for creating a family of related products.
 * In Factory Method, it is usually a single product line. But for Abstract Factory, it is multiple
 * product lines. The factory acts as as Super Factory, providing interfaces to make different products.
 * 
 * What problems does it solve?
 * - Different factories makes a group of related products
 * - Each factory can change the way they make their products independently
 * - Extensible and flexible, to add a new group, simply create a new subclass factory and
 *   new subclasses for new products, existing code need not be modified and risked introducing bugs
 * 
 * Principles
 * - Concept: factories are supposed to be experts in making products
 * - Hide object creation logic from client, who does not have expertise in building the products
 * 
 * Steps
 * - Define an interface or abstract class for multiple products
 * - Define concrete subclasses for each product line
 * - Define an interface or abstract class for the factory
 * - Define concrete subclasses for several specific factories, each focuses to build a family of 
 *   related products
 * - Use the factories to make the products instead of making the products myself
 * 
 * Pros
 * - separate object creation from client, allowing more flexibility and maintainability
 * - new product types can be easily added without altering client code (risk introducing bugs)
 * - hides product classes from clients, reduce dependencies
 * - client does not need to know individual related products, changing the factory allows for different
 *   related products to be grouped together, ensuring the application uses objects from only one family
 *   at a time.
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
 * - Cross-Platform UI toolkit
 *   Products: buttons, drop-down boxes, text fields
 *   Families: WindowsUI, MacUI, LinuxUI
 * - Themed Applications
 *   Products: Header, Footer, Sidebar
 *   Families: Dark Theme, Light Theme, High Constrast Theme
 * - Game Development
 *   Products: Enemy, Weapon, PowerUp
 *   Families: FantasyLevel, SciFiLevel, HorrorLevel
 * - Reporting
 *   Products: TableRenderer, ChartRenderer, TextFormatter
 *   Families: PDFReport, HTMLReport, CSVReport
 */

 /*
 * Extensibility and Flexibility:
 *    for this design pattern, each of the abstract and concrete classes can be in individual files
 *    hence when there are new products, none of the existing files need to be modified
 *    you just have to define new products and factories in new files
 *    this makes the design more flexible and extensible and less error prone as the core logic remains
 */

abstract class Soap {
    public abstract void clean();
}

class CheapSoap extends Soap {
    @Override
    public void clean() {
        System.out.println("I am a cheap soap but still I make your hands and body clean.");
    }
}

class OrganicSoap extends Soap {
    @Override
    public void clean() {
        System.out.println("I'm an expensive soap but I'm organic, good and healthy for your skin.");
    }
}

abstract class Shampoo {
    public abstract void wash();
}

class CheapShampoo extends Shampoo {
    @Override
    public void wash() {
        System.out.println("I'm a cheap shampoo but still I can make your hair clean.");
    }
}

class OrganicShampoo extends Shampoo {
    @Override
    public void wash() {
        System.out.println("I'm and expensive organic shampoo but I'm better for your hair.");
    }
}

abstract class PersonalCareFactory {
    //Composition, with different generic products
    public abstract Soap makeSoap();
    public abstract Shampoo makeShampoo();
}

class CheapPersonalCareFactory extends PersonalCareFactory {
    // but each factory makes a family of related products
    // cheap factory only makes cheap products

    @Override
    public Soap makeSoap() {
        System.out.println("Making cheap soap");
        return new CheapSoap();
    }

    @Override
    public Shampoo makeShampoo() {
        System.out.println("Making cheap shampoo");
        return new CheapShampoo();
    }
}

class OrganicPersonalCareFactory extends PersonalCareFactory {
    //organic factory only products a family of organic products
    @Override
    public Soap makeSoap() {
        System.out.println("Making organic soap");
        return new OrganicSoap();
    }

    @Override
    public Shampoo makeShampoo() {
        System.out.println("Making organic shampoo");
        return new OrganicShampoo();
    }
}

public class AbstractFactoryAbstractMain {
    public static void main(String[] args) {
        PersonalCareFactory cheapFactory = new CheapPersonalCareFactory();
        PersonalCareFactory organicFactory = new OrganicPersonalCareFactory();
        // Cheap family
        Soap cheapSoap = cheapFactory.makeSoap();
        Shampoo cheapShampoo = cheapFactory.makeShampoo();
        // Organic family
        Soap organicSoap = organicFactory.makeSoap();
        Shampoo organicShampoo = organicFactory.makeShampoo();

        cheapSoap.clean();
        cheapShampoo.wash();
        organicSoap.clean();
        organicShampoo.wash();
    }
}