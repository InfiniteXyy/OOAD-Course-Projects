package abstract_factory;

public class FactoryB extends Factory {

  @Override
  public Product createProduct(String productType) {
    switch (productType) {
      case "A":
        return new A("B");
      case "B":
        return new B("B");
      default:
        return null;
    }
  }
}
