package game;

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
          Game game = new Game();
          score += game.start();
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
