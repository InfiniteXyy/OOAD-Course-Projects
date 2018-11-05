package gui.store;

import game.Game;
import game.Player;
import java.util.ArrayList;
import java.util.List;

public class State {

  public static String STAGE_READY = "ready";
  public static String STAGE_PLAYING = "drawing card";
  public static String STAGE_GAME_RESULT = "game result";

  public String stage;
  public Game game;
  public Player winner;
  public List<Player> players;
  public int[] deltaMoney;
  public int curPlayerIndex;
  public boolean hasConfiged;

  State() {
    curPlayerIndex = 0;
    players = new ArrayList<>();
    this.stage = STAGE_READY;
    hasConfiged = false;
  }

  public Player getCurPlayer() {
    if (players.size() == 0) {
      return null;
    }
    return players.get(curPlayerIndex);
  }

  public int getNextPlayer() {
    if (curPlayerIndex + 1 < players.size()) {
      return curPlayerIndex + 1;
    } else {
      for (int i = 0; i < players.size(); i++) {
        if (players.get(i).isDrawing()) {
          return i;
        }
      }
    }
    return -1;
  }

  public boolean judgeGame() {
    for (Player player : players) {
      if (player.isDrawing()) {
        return false;
      }
    }
    winner = game.getWinner();
    deltaMoney = game.getDeltaMoney();
    for (int i = 0; i < players.size(); i++) {
      players.get(i).addMoney(deltaMoney[i]);
    }
    return true;
  }

}
