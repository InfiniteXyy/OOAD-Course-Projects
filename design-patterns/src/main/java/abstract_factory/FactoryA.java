package abstract_factory;

public class FactoryA extends Factory {


  @Override
  public Product createProduct(String productType) {
    switch (productType) {
      case "A":
        return new A("A");
      case "B":
        return new B("A");
      default:
        return null;
    }
  }
}
