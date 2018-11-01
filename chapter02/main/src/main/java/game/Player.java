package game;

import java.util.ArrayList;
import java.util.List;

public class Player {

  private static int _ID = 0;
  private int userId;
  private List<Integer> cards;
  private boolean isDrawing;

  Player() {
    this.userId = _ID++;
    cards = new ArrayList<>();
    isDrawing = true;
  }

  public int getUserId() {
    return userId;
  }

  public boolean isDrawing() {
    return isDrawing;
  }


  public List<Integer> getCards() {
    return cards;
  }

  public int getCardSum() {
    int result = 0;
    for (int temp : cards) {
      result += temp;
    }
    return result;
  }

  void addCard(int card) {
    this.cards.add(card);
  }

  void setStopDrawing() {
    isDrawing = false;
  }
}
