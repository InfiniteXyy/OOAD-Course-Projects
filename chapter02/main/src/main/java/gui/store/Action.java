package gui.store;

public class Action {

  public static final String TYPE_GAMING = "GAME";
  public static final String TYPE_STAGE = "STAGE";

  public String type;
  public String action;

  private Action(String type, String action) {
    this.type = type;
    this.action = action;
  }

  public static Action game(String action) {
    return new Action(TYPE_GAMING, action);
  }

  public static Action stage(String action) {
    return new Action(TYPE_STAGE, action);
  }

  @Override
  public String toString() {
    return "Action{" +
        "type='" + type + '\'' +
        ", action='" + action + '\'' +
        '}';
  }
}
