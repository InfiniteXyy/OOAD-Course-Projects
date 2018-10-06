package chapter01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  private static Config createConfigFromArgs(String[] args) throws Exception {
    int antNum = args.length - 2;
    List<Integer> antPositions = new ArrayList<>();
    for (int i = 0; i < antNum; i++) {
      antPositions.add(Integer.valueOf(args[i + 2]));
    }
    return Config
        .createConfig(Integer.valueOf(args[0]), Integer.valueOf(args[1]), antPositions);
  }

  private static void runGame(Config config) throws Exception {
    CreepingGame game = new CreepingGame(config);
    game.simulateAllSituations();
  }


  public static void main(String[] args) throws Exception {
    String gameType = args.length == 0 ? "null" : args[0];
    switch (gameType) {
      case "demo": {
        Config config = Config.createDemoConfig();
        runGame(config);
        break;
      }
      case "game": {
        Config config = createConfigFromArgs(Arrays.copyOfRange(args, 1, args.length));
        runGame(config);
        break;
      }
      default:
        System.out.println(gameType + " is not valid game type!");
        break;
    }
  }
}
