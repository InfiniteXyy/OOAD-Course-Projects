package cli;

import game.Game;
import game.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameCli {

  private static Scanner scanner = new Scanner(System.in);
  private List<Player> players = new ArrayList<>();

  private void printMoney() {
    br();
    System.out.println("金额面板");
    for (Player player : players) {
      System.out.println("玩家" + player.getUserId() + "：余额：" + player.getMoney());
    }
    br();
  }

  private void play(int player_number) {
    // 添加 n 个玩家
    for (int i = 0; i < player_number; i++) {
      players.add(new Player());
    }
    boolean running = true;
    while (running) {
      System.out.println("请选择一个数字！");
      System.out.println("1、开始游戏");
      System.out.println("2、查看金额");
      System.out.println("3、结束游戏");
      switch (scanner.nextInt()) {
        case 1:
          playOneGame();
          break;
        case 2:
          printMoney();
          break;
        case 3:
          running = false;
          System.out.println("Bye!");
          break;
        default:
          System.out.println("输入错误！");
          break;
      }
    }
  }

  private void playOneGame() {
    br();
    Game game = Game.createGame(players);
    for (Player player : players) {
      // 收钱，初始化
      player.reset();
      int putMoney;
      while (true) {
        System.out.println("玩家" + player.getUserId() + "：赌多少钱？");
        putMoney = scanner.nextInt();
        if (putMoney < 0 || putMoney > player.getMoney()) {
          System.out.println("金额错误，请重新输入");
        } else {
          player.setMoneyOnDesk(putMoney);
          break;
        }
      }
      System.out.println("你投入了 " + putMoney + " 元");


      // 赌博现场
      while (player.isDrawing()) {
        if(player.getCardSum() > 21) {
          player.setStopDrawing();
          System.out.println("超过21了！\n手牌和：" + player.getCardSum());
          break;
        }
        System.out.println("玩家" + player.getUserId() + "：你是否要抽牌？1 代表抽牌");
        if (scanner.next().equals("1")) {
          game.drawCardForPlayer(player);
          System.out.println("玩家" + player.getUserId() + "的手牌：" + player.getCards());
        } else {
          player.setStopDrawing();
          System.out.println("手牌和：" + player.getCardSum());
        }
      }
      System.out.println("玩家" + player.getUserId() + "回合结束\n-------------\n");
    }

    // 结束现场
    br();
    System.out.println("游戏结束！");
    System.out.println("电脑的手牌是：" + game.getComputerPlayer().getCards());
    for (Player player : players) {
      System.out.println("玩家" + player.getUserId() + "的结果：" + player.getCardSum());
    }
    Player winner = game.getWinner();
    System.out.println("最后的赢家是：玩家" + winner.getUserId());

    // 发钱现场
    br();
    System.out.println("金额变动");
    int[] dMoney = game.getDeltaMoney();
    for (int i = 0; i < players.size(); i++) {
      Player p = players.get(i);
      System.out.println("玩家" + p.getUserId() + "：" +
          p.getMoney() + " + " + dMoney[i] + " -> " + (p.getMoney() + dMoney[i]));
      p.addMoney(dMoney[i]);
    }
    br();
  }

  public static void main(String[] args) {
    System.out.println("请输入玩家数量！");
    int number = scanner.nextInt();
    new GameCli().play(number);
  }

  private void br() {
    System.out.println("\n==================================\n");
  }
}
