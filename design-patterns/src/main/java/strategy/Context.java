package strategy;

public class Context {
  // important
  private NumberStrategy operation;

  public Context(NumberStrategy operation) {
    this.operation = operation;
  }

  public int execute(int a, int b) {
    return operation.doOperation(a, b);
  }

}
