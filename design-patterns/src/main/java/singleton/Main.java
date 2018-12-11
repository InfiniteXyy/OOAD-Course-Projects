package singleton;

public class Main {

  public static void main(String[] args) {
    SingletonClass a = SingletonClass.getInstance();
    SingletonClass b = SingletonClass.getInstance();
    System.out.println("a == b is " + (a == b));
  }
}
