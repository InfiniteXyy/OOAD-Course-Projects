package gui.store;

import game.Game;
import game.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// game.Game 类即为 reducer
public class Store {

  public final static String STAGE_LISTENER = "stage_listener";
  private static Store store = new Store();
  private Map<String, List<Runnable>> listeners;

  public Store() {
    this.listeners = new HashMap<>();
    this.listeners.put(STAGE_LISTENER, new ArrayList<>());
    this.state = new State();
  }

  public static Store getInstance() {
    return store;
  }

  public State state;


  public Runnable subscribe(String type, Runnable listener) {
    listeners.get(type).add(listener);
    return () -> this.listeners.get(type).remove(listener);
  }

  public Runnable subscribe(Runnable listener) {
    return subscribe(STAGE_LISTENER, listener);
  }


  public void dispatch(String action) {
    if (this.state.stage.equals(State.STAGE_READY)) {
      switch (action) {
        case "START_GAME":
          this.state.game = Game.createGame(1);
          this.state.stage = State.STAGE_DRAWING;
          break;
        default:
          break;
      }
    } else if (this.state.stage.equals(State.STAGE_DRAWING)) {
      Game game = this.state.game;
      Player player = game.getCurrentPlayer();
      switch (action) {
        case "ADD_MY_CARD":
          game.drawCardForPlayer(player);
          break;
        case "QUIT_DRAW":
          game.stopDrawingForPlayer(player);
          if (!game.nextPlayer()) {
            // judge game
            this.state.gameResult = game.judgeGame();
            this.state.stage = State.STAGE_GAME_RESULT;
          } else {
            this.state.stage = State.STAGE_NEXT_PLAYER;
          }
          break;
        default:
          break;
      }
    } else if (this.state.stage.equals(State.STAGE_NEXT_PLAYER)) {
      switch (action) {
        case "NEXT_TURN":
          this.state.stage = State.STAGE_DRAWING;
      }
    }
    if (action.equals("END_GAME")) {
      this.state.stage = State.STAGE_READY;
    }

    for (Runnable listener : listeners.get(STAGE_LISTENER)) {
      listener.run();
    }
  }
}
