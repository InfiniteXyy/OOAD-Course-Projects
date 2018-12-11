package strategy;

public class Main {

  public static void main(String[] args) {
    // 同一个 Context 类，根据传入的不同行为，会有不同的操作
    Context context1 = new Context(new MinusStrategy());
    Context context2 = new Context(new PlusStrategy());
    System.out.println(context1.execute(10, 5));
    System.out.println(context2.execute(10, 5));
  }
}
