package gui.store;

import game.Game;
import game.Player;
import gui.view.components.GameConfigDialog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// game.Game 类即为 reducer
public class Store {

  private static Store store = new Store();
  private Map<String, List<Runnable>> listeners;

  public Store() {
    this.listeners = new HashMap<>();
    this.listeners.put(Action.TYPE_STAGE, new ArrayList<>());
    this.listeners.put(Action.TYPE_GAMING, new ArrayList<>());
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


  public void dispatch(Action action) {
    System.out.println(action);
    switch (action.type) {
      case Action.TYPE_GAMING:
        handleGameActions(action.action);
        break;
      case Action.TYPE_STAGE:
        handleStageActions(action.action);
        break;
    }

  }

  private void handleStageActions(String action) {
    switch (action) {
      case "START_GAME":
        this.state.players = Arrays.asList(new Player(), new Player());
        this.state.game = Game.createGame(this.state.players);
        this.state.stage = State.STAGE_DRAWING;
        new GameConfigDialog().setVisible(true);
        break;
      case "END_GAME":
        this.state.game = null;
        this.state.players = null;
        this.state.stage = State.STAGE_READY;
        break;
      case "NEXT_TURN":
        this.state.stage = State.STAGE_NEXT_PLAYER;
      default:
        break;
    }
    for (Runnable listener : listeners.get(Action.TYPE_STAGE)) {
      listener.run();
    }
  }

  private void handleGameActions(String action) {
    Game game = this.state.game;
    Player player = this.state.curPlayer;
    switch (action) {
      case "ADD_MY_CARD":
        game.drawCardForPlayer(player);
        break;
      case "QUIT_DRAW":

        this.handleStageActions("");
        break;
      default:
        break;
    }
    for (Runnable listener : listeners.get(Action.TYPE_GAMING)) {
      listener.run();
    }
  }
}
