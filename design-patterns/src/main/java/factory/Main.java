package factory;

public class Main {

  public static void main(String[] args) {
    Product a = Factory.createProduct("A");
    System.out.println(a.describe());

    Product b = Factory.createProduct("B");
    System.out.println(b.describe());
  }
}
