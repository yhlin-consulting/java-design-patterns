/*
 * Abstract Factory Design Pattern
 * - Interface implementation
 */

interface Soap {
    void clean();
}

class CheapSoap implements Soap {
    @Override
    public void clean() {
        System.out.println("I am a cheap soap but still I make your hands and body clean.");
    }
}

class OrganicSoap implements Soap {
    @Override
    public void clean() {
        System.out.println("I'm an expensive soap but I'm organic, good and healthy for your skin.");
    }
}

interface Shampoo {
    void wash();
}

class CheapShampoo implements Shampoo {
    @Override
    public void wash() {
        System.out.println("I'm a cheap shampoo but still I can make your hair clean.");
    }
}

class OrganicShampoo implements Shampoo {
    @Override
    public void wash() {
        System.out.println("I'm and expensive organic shampoo but I'm better for your hair.");
    }
}

interface PersonalCareFactory {
    //Composition, with different generic products
    public abstract Soap makeSoap();
    public abstract Shampoo makeShampoo();
}

class CheapPersonalCareFactory implements PersonalCareFactory {
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

class OrganicPersonalCareFactory implements PersonalCareFactory {
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

public class AbstractFactoryInterfaceMain {
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