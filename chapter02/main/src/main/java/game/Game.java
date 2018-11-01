package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements GameInterface {

  private boolean isGaming;
  private List<Player> players;
  private ComputerPlayer computerPlayer;
  private int curPlayerIndex;
  private Deck deck;

  private Game() {
    isGaming = true;
    players = new ArrayList<>();
    this.deck = new Deck();
    computerPlayer = new ComputerPlayer();
    computerPlayer.fillCards(deck);
  }

  private void initPlayers(int num) {
    assert num >= 1;
    for (int i = 0; i < num; i++) {
      players.add(new Player());
    }
    curPlayerIndex = 0;
  }


  public static Game createGame(int playerNum) {
    Game game = new Game();
    game.initPlayers(playerNum);
    return game;
  }

  @Override
  public void drawCardForPlayer(Player player) {
    player.addCard(deck.drawCard());
  }

  @Override
  public void stopDrawingForPlayer(Player player) {
    player.setStopDrawing();
  }

  @Override
  public Player getCurrentPlayer() {
    return players.get(curPlayerIndex);
  }

  @Override
  public ComputerPlayer getComputerPlayer() {
    return computerPlayer;
  }

  @Override
  public void nextPlayer() {
    if (curPlayerIndex + 1 == players.size()) {
      this.isGaming = false;
    } else {
      curPlayerIndex++;
    }
  }

  // 如果所有玩家都超 21 了，或者所有人类玩家都选择结束，就 isGaming = false;
  @Override
  public boolean judgeGame() {
    return new Random().nextBoolean();
  }

  @Override
  public boolean isGaming() {
    return this.isGaming;
  }
}
