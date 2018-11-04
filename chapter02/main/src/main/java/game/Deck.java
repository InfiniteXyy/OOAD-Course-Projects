package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Deck {
  private List<Card> cards;

  public Deck() {
    // init with cards
    this.cards = new ArrayList<>();
    for(int i = 1; i < 14; i++)
    {
      for(int j = 0; j < 4; j++)
      {
        Card card = new Card(j,i);
        this.cards.add(card);
      }
    }
    Collections.shuffle(cards);
  }

  Card drawCard() {
    Card card = this.cards.get(0);
    this.cards.remove(0);
    return card;
  }

}
