package game;

import java.util.Scanner;

public class HumanPlayer extends Player {

  private static Scanner scanner = new Scanner(System.in);

  @Override
  public String getCardInfo() {
    return this.getCards() + " sum: " + this.getCardSum();
  }

  @Override
  public void refreshDrawingState() {
    System.out.println("你是否要抽牌？1 代表抽牌");
    this.setDrawing(scanner.nextInt() == 1);
  }
}
