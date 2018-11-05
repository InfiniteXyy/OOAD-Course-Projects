package game;

import java.util.List;

public class ComputerPlayer extends Player {

  void fillCards(Deck deck) {
    while (this.getCardSum() <= 17) {
      this.addCard(deck.drawCard());
    }
  }

  @Override
  public List<Card> getCards() {
    if (isDrawing()) {
      return super.getCards().subList(0, 2);
    } else {
      return super.getCards();
    }
  }
}
