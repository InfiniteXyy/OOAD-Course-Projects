package gui.store;

import game.Game;

public class State {

  public static String STAGE_READY = "ready";
  public static String STAGE_DRAWING = "drawing card";
  public static String STAGE_NEXT_PLAYER = "next player";
  public static String STAGE_GAME_RESULT = "game result";


  public int score = 0;
  public String stage;
  public Game game;
  public boolean gameResult;

  State() {
    this.stage = STAGE_READY;
  }

}
