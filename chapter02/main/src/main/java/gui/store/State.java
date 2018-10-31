package gui.store;

import java.util.ArrayList;
import java.util.List;

public class State {

  public List<Integer> myCards;
  public List<Integer> yourCards;
  public int score = 0;
  public boolean gameRunning = false;

  public State() {
    myCards = new ArrayList<>();
    yourCards = new ArrayList<>();
  }
}
