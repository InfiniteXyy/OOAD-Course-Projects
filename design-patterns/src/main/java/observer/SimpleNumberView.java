package observer;

public class SimpleNumberView implements Observer {

  private SimpleNumberDataObject dataObject;

  public SimpleNumberView(SimpleNumberDataObject dataObject) {
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
}
