package strategy;

public class MinusStrategy implements NumberStrategy {

  @Override
  public int doOperation(int a, int b) {
    return a - b;
  }
}
