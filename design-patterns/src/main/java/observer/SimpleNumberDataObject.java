package observer;

import java.util.ArrayList;
import java.util.List;

public class SimpleNumberDataObject implements Subject {

  private List<Observer> observerList = new ArrayList<>();

  private int number = 0;

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
    publish();
  }

  @Override
  public void attach(Observer o) {
    observerList.add(o);
  }

  @Override
  public void detach(Observer o) {
    int index = observerList.indexOf(o);
    if (index >= 0) {
      observerList.remove(index);
    }
  }

  @Override
  public void publish() {
    for (Observer observer : observerList) {
      observer.update();
    }
  }
}
