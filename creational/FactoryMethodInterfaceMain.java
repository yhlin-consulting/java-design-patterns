interface Product {
    void doSomething();
}

class ProductA implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product A is doing something");
    }
}

class ProductB implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product B is doing something");
    }
}

interface Factory {
    Product createProduct();
}

class ProductAFactory implements Factory {
    @Override
    public Product createProduct() {
        return new ProductA();
    }
}

class ProductBFactory implements Factory {
    @Override
    public Product createProduct() {
        return new ProductB();
    }
}

public class FactoryMethodInterfaceMain {
    public static void main(String[] args) {
        Factory factoryA = new ProductAFactory();
        Product productA = factoryA.createProduct();
        Factory factoryB = new ProductBFactory();
        Product productB = factoryB.createProduct();

        productA.doSomething();
        productB.doSomething();
    }
}
