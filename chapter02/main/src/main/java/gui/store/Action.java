package gui.store;

public class Action {

  public static final String TYPE_GAMING = "GAME";
  public static final String TYPE_STAGE = "STAGE";
  public static final String TYPE_ALL = "ALL";
  public static final String TYPE_SWITCH = "SWITCH";

  public String type;
  public String action;
  public int optionalInt;

  public Action(String type, String action) {
    this.type = type;
    this.action = action;
  }

  public Action(String type, String action, int optionalInt) {
    this.type = type;
    this.action = action;
    this.optionalInt = optionalInt;
  }

  public static Action game(String action) {
    return new Action(TYPE_GAMING, action);
  }

  public static Action stage(String action) {
    return new Action(TYPE_STAGE, action);
  }

  public static Action pSwtich(String action) {
    return new Action(TYPE_SWITCH, action);
  }

  @Override
  public String toString() {
    return "Action{" +
        "type='" + type + '\'' +
        ", action='" + action + '\'' +
        '}';
  }
}
