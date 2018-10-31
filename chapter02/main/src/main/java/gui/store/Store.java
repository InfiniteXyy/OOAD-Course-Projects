package gui.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    switch (action) {
      case "ADD_MY_CARD":
        this.state.myCards.add(new Random().nextInt(13));
        break;
      case "ADD_COMPUTER_CARD":
        this.state.yourCards.add(new Random().nextInt(13));
        break;
      case "START_GAME":
        this.state.gameRunning = true;
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
