package abstract_factory;

public class FactoryProducer {

  public static Factory createFactory(String choice) {
    if (choice.equals("A")) {
      return new FactoryA();
    } else if (choice.equals("B")) {
      return new FactoryB();
    }
    return null;
  }

}
