package adapter;

public class Main {

  public static void main(String[] args) {
    Client client = new Client();
    IntAdapter adapter = new IntAdapter();
    client.printRandomInteger(adapter);

  }


}
