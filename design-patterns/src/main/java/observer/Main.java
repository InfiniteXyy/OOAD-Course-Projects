package observer;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

  public static void main(String[] args) {
    SimpleNumberDataObject dataObject = new SimpleNumberDataObject();
    SimpleNumberView view = new SimpleNumberView(dataObject);

    System.out.println("Continuously publish random numbers until it is less than 10");
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        int number = new Random().nextInt(100);
        dataObject.setNumber(number);
        if (number < 10) {
          System.out.println("unsubscribed");
          view.unsubscribe();
        }
      }
    }, 1000, 200);
  }
}
