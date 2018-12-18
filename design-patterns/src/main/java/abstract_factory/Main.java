package abstract_factory;

public class Main {

  public static void main(String[] args) {
    Factory factory = FactoryProducer.createFactory("A");
    Product product = factory.createProduct("A");
    System.out.println(product.describe());
  }
}
