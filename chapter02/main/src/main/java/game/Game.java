package game;

import java.util.List;

public class Game implements GameInterface {

  private List<Player> players;
  private ComputerPlayer computerPlayer;
  private Deck deck;
  private int winnerId;

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


  @Override
  public Player getWinner() {
    int maxvalue = computerPlayer.getCardSum();
    int winner = -1;
    //-1代表电脑玩家
    if (maxvalue > 21) {
      for (Player player : players) {
        if (player.getCardSum() <= 21) {
          maxvalue = player.getCardSum();
          winner = player.getUserId();
          break;
        }
      }
    }
    for (Player player : players) {
      if (player.getCardSum() > maxvalue && player.getCardSum() <= 21) {
        maxvalue = player.getCardSum();
        winner = player.getUserId();
      }
    }
    winnerId = winner;
    for (Player player : players) {
      if (player.getUserId() == winnerId) {
        return player;
      }
    }
    return computerPlayer;
  }

  //TODO: 根据胜负情况与玩家投入的钱（money on desk）输出 delta 金额数组
  @Override
  public int[] getDeltaMoney() {
    int[] delta = new int[players.size()];

    int i = 0;
    int change = 0;
    for (Player player : players) {
      if (player.getUserId() == winnerId) {
        change = player.getMoneyOnDesk();
      } else {
        change = 0 - player.getMoneyOnDesk();
      }
      delta[i] = change;
      i++;
    }
    return delta;
  }


}
