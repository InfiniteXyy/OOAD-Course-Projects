package cli;

import game.Game;
import game.Player;
import java.util.Scanner;

public class GameCli {

  private int score = 0;
  private static Scanner scanner = new Scanner(System.in);

  public void printScore() {
    System.out.println("你的比分是：" + score);
  }

  public void play() {
    System.out.println("请选择一个数字！");
    System.out.println("1、开始游戏");
    System.out.println("2、查看比分");
    System.out.println("3、结束游戏");
    boolean running = true;
    while (running) {
      switch (scanner.nextInt()) {
        case 1:
          Game game = Game.createGame(1);
          while (game.isGaming()) {
            Player player = game.getCurrentPlayer();
            if (player.isDrawing()) {
              System.out.println("你是否要抽牌？1 代表抽牌");
              if (scanner.next().equals("1")) {
                game.drawCardForPlayer(player);
                System.out.println("当前用户手牌：" + player.getCards());
              } else {
                game.stopDrawingForPlayer(player);
              }
            } else {
              game.nextPlayer();
              System.out.println("玩家" + player.getUserId() + " 的回合结束！他的和是 " + player.getCardSum());
            }
          }
          System.out.println("游戏结束！");
          System.out.println("电脑的手牌是：" + game.getComputerPlayer().getCards());
          System.out.println(game.judgeGame() ? "你赢" : "电脑赢");
          break;
        case 2:
          printScore();
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

  public static void main(String[] args) {
    new GameCli().play();
  }
}
