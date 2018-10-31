package gui.view.components;

import gui.common.GlobalFont;
import gui.view.Utils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class CardView {

  private Rectangle cardBody;
  private int value;

  public CardView(int gridX, int gridY, int value) {
    this.value = value;
    cardBody = new Rectangle(16 + gridX * 96, 16 + gridY * 200, 80, 120);
  }

  public void draw(Graphics2D g) {
    this.drawCard(g);
    Utils.drawTextCenterInRect(g, cardBody, String.valueOf(value), "h1");
  }

  private void drawCard(Graphics2D g) {
    g.setColor(Color.decode("#aaaaaa"));
    g.fill(cardBody);
    g.setColor(Color.BLACK);
    g.setStroke(new BasicStroke(3));
    g.draw(cardBody);
  }


}
