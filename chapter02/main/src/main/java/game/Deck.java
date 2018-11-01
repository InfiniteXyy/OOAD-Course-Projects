package game;

import java.util.List;
import java.util.Random;

class Deck {

  private static Random random = new Random();

  private List<Integer> cards;

  Deck() {
    // init with cards
  }

  int drawCard() {
    return random.nextInt(13);
  }

}
