package observer;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleNumberView implements Observer {

  private SimpleNumberDataObject dataObject;

  public void subscribe(SimpleNumberDataObject dataObject) {
    if (this.dataObject != null) {
      unsubscribe();
    }
    this.dataObject = dataObject;
    dataObject.attach(this);
  }

  public void unsubscribe() {
    if (this.dataObject != null) {
      this.dataObject.detach(this);
      this.dataObject = null;
    }
  }

  @Override
  public void update() {
    System.out.println("update to " + this.dataObject.getNumber());
  }

  public static void main(String[] args) {
    SimpleNumberView view = new SimpleNumberView();
    SimpleNumberDataObject dataObject = new SimpleNumberDataObject();
    view.subscribe(dataObject);

    System.out.println("publish random numbers until it is less than 10");
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
