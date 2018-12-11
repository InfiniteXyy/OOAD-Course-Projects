package facade;

public class Main {

  public static void main(String[] args) {
    SystemFacade facade = new SystemFacade();
    System.out.println(facade.getAllData());
    System.out.println(facade.getA());
    System.out.println(facade.getB());
    System.out.println(facade.getC());
  }

}
