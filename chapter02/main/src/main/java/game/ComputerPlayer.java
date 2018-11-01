package game;

public class ComputerPlayer extends Player {

  public void fillCards(Deck deck) {
    while (this.getCardSum() <= 17) {
      this.addCard(deck.drawCard());
    }
  }
}
