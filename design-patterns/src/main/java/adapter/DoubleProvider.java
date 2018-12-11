package adapter;

import java.util.Random;

public class DoubleProvider {

  public Double getDouble() {
    return new Random().nextDouble() * 1000;
  }

}
