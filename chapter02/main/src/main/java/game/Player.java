package game;

import java.util.ArrayList;
import java.util.List;

public class Player {

  private static int _ID = 0;
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
    for (Card temp : cards) {
      result += temp.getValue();
    }
    return result;
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
}
