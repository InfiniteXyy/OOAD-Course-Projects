package abstract_factory;

public class A implements Product {

  private String from;

  A(String from) {
    this.from = from;
  }

  @Override
  public String describe() {
    return "I am product A from factory " + from;
  }
}
