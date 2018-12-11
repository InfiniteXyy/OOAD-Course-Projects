package facade;

public class SystemFacade {

  private SystemA a = new SystemA();
  private SystemB b = new SystemB();
  private SystemC c = new SystemC();

  public String getAllData() {
    return a.getData() + b.getData() + c.getData();
  }

  public String getA() {
    return a.getData();
  }

  public String getB() {
    return b.getData();
  }

  public String getC() {
    return c.getData();
  }

}
