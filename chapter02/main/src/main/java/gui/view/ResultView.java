package gui.view;

import game.Card;
import game.Player;
import gui.common.GlobalFont;
import gui.store.Store;
import gui.view.components.CardView;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class ResultView extends JPanel {

  private Store store = Store.getInstance();

  public ResultView() {
    setBackground(Color.decode("#44617b"));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Player currentPlayer = store.state.curPlayer;
    Player computer = store.state.game.getComputerPlayer();
    List<CardView> computerCards = new ArrayList<>();
    List<CardView> myCards = new ArrayList<>();
    List<Card> stateMe = currentPlayer.getCards();
    List<Card> stateComputer = computer.getCards();
    for (int i = 0; i < stateMe.size(); i++) {
      myCards.add(new CardView(i, 1, stateMe.get(i).getValue()));
    }
    for (int i = 0; i < stateComputer.size(); i++) {
      computerCards.add(new CardView(i, 0, stateComputer.get(i).getValue()));
    }
    Graphics2D g2 = (Graphics2D) g;
    for (CardView cardView : computerCards) {
      cardView.draw(g2);
    }
    for (CardView cardView : myCards) {
      cardView.draw(g2);
    }
    g2.setFont(GlobalFont.H2());
    g2.setColor(Color.WHITE);
    String result = computer.getCardSum() + " vs " + currentPlayer.getCardSum();
    result += "  ";
    result += store.state.gameResult ? "胜利！" : "失败！";
    g2.drawString(result, 16, 400);
  }
}
