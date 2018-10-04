package chapter01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreepingGame {

  private static Random random = new Random();
  private Config config;

  public CreepingGame(Config config) {
    this.config = config;
  }

  public int start(char[] directions) throws Exception {
    // set ants
    int v = config.getAntVelocity();
    List<Ant> ants = new ArrayList<>();
    for (int p : config.getAntPositions()) {
      ants.add(new Ant(v, p));
    }
    // set stick
    Stick stick = new Stick(config.getStickLength());
    stick.setAnts(ants);
    stick.initDirection(directions);

    // start
    System.out.println("directions: " + Arrays.toString(directions));
    int step = 0;
    while (stick.hasAnt()) {
      stick.nextFrame();
      stick.printFrame();
      step++;
    }
    System.out.println("step: " + step + "\n");
    return step;
  }

  public List<Integer> getAllSituationSteps() throws Exception {
    // use number to set directions
    // 1 0 0 0 1 => 19
    // 0 ~ 31 => 32 situations
    List<Integer> ans = new ArrayList<>();
    int situationNum = 1 << config.getAntNumber();
    for (int i = 0; i < situationNum; i++) {
      char[] directions = String.format("%0"+config.getAntNumber()+"d", Integer.valueOf(Integer.toString(i, 2)))
          .toCharArray();
      ans.add(start(directions));
    }
    return ans;
  }

  public int randomDemo() throws Exception {
    char[] directions = new char[config.getAntNumber()];
    for (int i = 0; i < directions.length; i++) {
      directions[i] = random.nextBoolean() ? '1' : '0';
    }

    return start(directions);
  }
}

