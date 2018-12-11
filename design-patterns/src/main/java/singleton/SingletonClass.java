package singleton;

public class SingletonClass {

  private SingletonClass() {
  }

  private static SingletonClass singletonClass;

  public static SingletonClass getInstance() {
    if (singletonClass == null) {
      singletonClass = new SingletonClass();
    }
    return singletonClass;
  }
}
