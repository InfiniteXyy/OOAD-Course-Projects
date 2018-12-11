package strategy;

public class PlusStrategy implements NumberStrategy {

  @Override
  public int doOperation(int a, int b) {
    return a + b;
  }
}
