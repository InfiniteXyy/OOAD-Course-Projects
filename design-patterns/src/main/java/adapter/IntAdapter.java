package adapter;

public class IntAdapter implements IntTarget {

  private DoubleProvider doubleProvider = new DoubleProvider();

  @Override
  public Integer getInteger() {
    return doubleProvider.getDouble().intValue();
  }
}
