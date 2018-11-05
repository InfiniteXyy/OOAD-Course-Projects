package game;

public class

Card {

  private static final int SPADE = 0;
  private static final int HEART = 1;
  private static final int DIAMOND = 2;
  private static final int CLUB = 3;
  private static final String[] suitEmoji = {"♠", "♥", "♦", "♣"};
  private static final String[] suit = {"S", "H", "D", "C"};
  private static final String[] resouseId = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J",
      "Q", "K"};
  private static final String[] valueID = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J",
      "Q", "K"};
  private int type;
  private int value;

  public Card(int type, int value) {
    assert value >= 1 && value <= 13;
    this.type = type;
    this.value = value;
  }

  public String getSuit(boolean needEmoji) {
    return needEmoji ? suitEmoji[type] : suit[type];
  }

  public String getResourceName() {
    return getSuit(false) + resouseId[value - 1];
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return getSuit(true) + valueID[getValue() - 1];
  }
}
