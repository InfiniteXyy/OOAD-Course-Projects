import java.util.ArrayList;
import java.util.List;

public abstract class Player {

  private List<Integer> cards;
  private boolean isDrawing;

  public Player() {
    cards = new ArrayList<>();
    isDrawing = true;
  }

  public boolean isDrawing() {
    return isDrawing;
  }

  public void setDrawing(boolean drawing) {
    isDrawing = drawing;
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

  public void addCard(int card) {
    this.cards.add(card);
  }

  public abstract String getCardInfo();

  public abstract void refreshDrawingState();
}
