package gui.view.components;

import game.Card;
import game.Player;
import gui.store.Store;
import gui.view.Utils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.JPanel;

public class CardDrawingPanel extends JPanel {

  private Store store = Store.getInstance();

  public CardDrawingPanel() {
    setBackground(Color.decode("#44617b"));

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Player p = store.state.getCurPlayer();
    List<Card> cards = p.getCards();
    Graphics2D g2 = (Graphics2D) g;
    Rectangle r = g2.getClipBounds();
    r.height /= 3;
    g2.setColor(Color.WHITE);
    Utils.drawTextCenterInRect(g2, r, "玩家" + p.getUserId() + "的和：" + p.getCardSum(), "h2");
    for (int i = 0; i < cards.size(); i++) {
      new CardView(i, cards.get(i)).draw(g2);
    }
  }
}
