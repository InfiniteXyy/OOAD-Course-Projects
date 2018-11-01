package gui.store;

import game.Game;
import game.Player;
import java.util.ArrayList;
import java.util.List;

// game.Game 类即为 reducer
public class Store {

  private static Store store = new Store();
  private List<Runnable> listeners;

  public Store() {
    this.listeners = new ArrayList<>();
    this.state = new State();
  }

  public static Store getInstance() {
    return store;
  }

  public State state;


  public Runnable subscribe(Runnable listener) {
    listeners.add(listener);
    return () -> this.listeners.remove(listener);
  }

  public void dispatch(String action) {
    Game game = null;
    Player player = null;
    if (this.state.gameRunning) {
      game = this.state.game;
      player = game.getCurrentPlayer();
    }
    switch (action) {
      case "ADD_MY_CARD":
        if (this.state.gameRunning) {
          game.drawCardForPlayer(player);
          this.state.myCards = new ArrayList<>(player.getCards());
        }
        break;
      case "QUIT_DRAW":
        if (this.state.gameRunning) {
          game.stopDrawingForPlayer(player);
        }
        break;
      case "START_GAME":
        this.state.gameRunning = true;
        this.state.game = Game.createGame(1);
        break;
      case "END_GAME":
        this.state.gameRunning = false;
        this.state.myCards.clear();
        this.state.yourCards.clear();
        break;
      default:
        break;
    }
    for (Runnable runnable : listeners) {
      runnable.run();
    }
  }
}
