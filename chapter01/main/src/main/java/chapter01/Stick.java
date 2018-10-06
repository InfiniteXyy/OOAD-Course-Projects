package chapter01;

import java.util.Arrays;
import java.util.List;

public class Stick {

  private int length;
  private List<Ant> ants;

  public Stick(int length) {
    this.length = length;
  }

  public void setAnts(List<Ant> ants) {
    this.ants = ants;
  }

  public List<Ant> getAnts() {
    return ants;
  }

  public boolean hasAnt() {
    for (Ant ant : ants) {
      if (ant.isAlive()) {
        return true;
      }
    }
    return false;
  }

  public void nextFrame() {
    for (Ant ant : ants) {
      if (!ant.isAlive()) {
        continue;
      }
      int pos = ant.creep();
      if (pos >= length || pos <= 0) {
        ant.setAlive(false);
      }
    }
    // collide
    for (int i = 0; i < ants.size(); i++) {
      for (int j = i + 1; j < ants.size(); j++) {
        Ant ant1 = ants.get(i);
        Ant ant2 = ants.get(j);
        if (ant1.getPosition() == ant2.getPosition()) {
          ant1.collide();
          ant2.collide();
        }
      }
    }
  }

  public String printFrame() {
    return Arrays.toString(ants.stream().map(ant -> ant.getPosition()).toArray());
  }

  public void initDirection(char[] directions) throws Exception {
    if (directions.length != ants.size()) {
      throw new Exception("Directions numbers Error");
    }
    for (int i = 0; i < ants.size(); i++) {
      if (directions[i] == '0') {
        ants.get(i).collide();
      }
    }
  }
}
