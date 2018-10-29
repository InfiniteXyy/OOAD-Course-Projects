import java.util.List;
import java.util.Random;

public class Deck {

  static Random random = new Random();

  private List<Integer> cards;

  public Deck() {
    // init with cards
  }

  public int drawCard() {
    return random.nextInt(13) + 1;
  }

}
