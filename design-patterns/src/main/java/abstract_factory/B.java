package abstract_factory;

public class B implements Product {

  private String from;

  B(String from) {
    this.from = from;
  }

  @Override
  public String describe() {
    return "I am product B from factory " + from;
  }
}
