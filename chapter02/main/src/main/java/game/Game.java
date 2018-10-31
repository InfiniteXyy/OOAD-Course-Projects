package game;

import java.util.Random;

public class Game {

  public boolean judge(Player master, Player pingmin) {
    return new Random().nextBoolean();
  }

  public int start() {
    Deck deck = new Deck();
    Player me = new HumanPlayer();
    Player computer = new ComputerPlayer();
    System.out.println("游戏开始！");
    // 抽牌过程
    while (me.isDrawing() || computer.isDrawing()) {
      computer.refreshDrawingState();
      if (me.isDrawing()) {
        me.refreshDrawingState();
      }

      if (computer.isDrawing()) {
        int card = deck.drawCard();
        computer.addCard(card);
      }
      if (me.isDrawing()) {
        int card = deck.drawCard();
        me.addCard(card);
      }

      // 打印手牌
      System.out.println(computer.getCardInfo());
      System.out.println(me.getCardInfo());
    }

    if (judge(computer, me)) {
      System.out.println("庄家赢");
    } else {
      System.out.println("你赢");
    }

    return 0;
  }

}
