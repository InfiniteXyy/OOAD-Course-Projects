package factory;

public class Factory {

  public static Product createProduct(String productType) {
    switch (productType) {
      case "A":
        return new A();
      case "B":
        return new B();
      default:
        return null;
    }

  }

}
