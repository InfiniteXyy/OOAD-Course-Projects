package game;

import java.util.ArrayList;
import java.util.List;

public class Player {

  private static int _ID = 1;
  private int userId;
  private List<Card> cards;
  private boolean isDrawing;
  private int money;
  private int moneyOnDesk;

  public Player() {
    this.userId = _ID++;
    cards = new ArrayList<>();
    isDrawing = true;
    money = 10; // 初始为石原里美
  }

  public void addMoney(int money) {
    this.money += money;
  }

  public int getMoney() {
    return money;
  }

  public int getUserId() {
    return userId;
  }

  public boolean isDrawing() {
    return isDrawing;
  }


  public List<Card> getCards() {
    return cards;
  }

  public int getCardSum() {
    int result = 0;
    int num_A = 0;
    for (Card temp : cards) {
      result += temp.getValue();
      if (temp.getValue() == 1) {
        num_A++;
      }
    }
    if (num_A >= 1) {
      if (result + 10 > 21) {
        return result;
      } else {
        return result + 10;
      }
    } else {
      return result;
    }
  }

  void addCard(Card card) {
    this.cards.add(card);
  }

  public void setStopDrawing() {
    isDrawing = false;
  }

  public void reset() {
    this.cards.clear();
    this.isDrawing = true;
  }

  public int getMoneyOnDesk() {
    return moneyOnDesk;
  }

  public void setMoneyOnDesk(int moneyOnDesk) {
    this.moneyOnDesk = moneyOnDesk;
  }

  public String showCards() {
    StringBuilder sb = new StringBuilder();
    for (Card card : getCards()) {
      sb.append(card);
      sb.append(" ");
    }
    if (!isDrawing) {
      sb.append("(").append(getCardSum()).append(")");
    }
    return sb.toString();
  }
}
