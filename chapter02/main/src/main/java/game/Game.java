package game;

import java.util.List;
import java.util.Random;

public class Game implements GameInterface {

  private List<Player> players;
  private ComputerPlayer computerPlayer;
  private Deck deck;

  private Game() {
    this.deck = new Deck();
    computerPlayer = new ComputerPlayer();
    computerPlayer.fillCards(deck);
  }

  public static Game createGame(List<Player> players) {
    Game game = new Game();
    game.players = players;
    return game;
  }

  @Override
  public void drawCardForPlayer(Player player) {
    player.addCard(deck.drawCard());
  }

  @Override
  public ComputerPlayer getComputerPlayer() {
    return computerPlayer;
  }

  //TODO: 根据 players 列表判断胜负，返回最后的胜利者
  @Override
  public Player getWinner() {
    if (new Random().nextBoolean()) {
      return computerPlayer;
    } else {
      return players.get(new Random().nextInt(players.size()));
    }
  }

  //TODO: 根据胜负情况与玩家投入的钱（money on desk）输出 delta 金额数组
  @Override
  public int[] getDeltaMoney() {
    int[] delta = new int[players.size()];
    Random random = new Random();
    int i = 0;
    for (Player player : players) {
      delta[i++] = random.nextInt(10);
    }
    return delta;
  }


}
