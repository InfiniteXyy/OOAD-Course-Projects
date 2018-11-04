package game;

import java.util.List;
import java.util.Random;

class Deck {

  private static Random random = new Random();

  private List<Integer> cards;

  Deck() {
    // init with cards
  }

  Card drawCard() {
    return new Card(random.nextInt(4), random.nextInt(13) + 1);
  }

}
