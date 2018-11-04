package gui.store;

import game.Game;
import game.Player;
import java.util.List;

public class State {

  public static String STAGE_READY = "ready";
  public static String STAGE_DRAWING = "drawing card";
  public static String STAGE_NEXT_PLAYER = "next player";
  public static String STAGE_GAME_RESULT = "game result";

  public String stage;
  public Game game;
  public boolean gameResult;
  public List<Player> players;
  public Player curPlayer;
  State() {
    this.stage = STAGE_READY;
  }

}
