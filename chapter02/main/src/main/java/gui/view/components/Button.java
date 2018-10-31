package gui.view.components;

import gui.view.Utils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class Button {

  private String text;
  private static final int BTN_WIDTH = 100;
  private static final int BTN_HEIGHT = 35;
  private Rectangle r;
  private Runnable onClick;

  public Button(int x, int y, String text, Runnable onClick) {
    this.text = text;
    r = new Rectangle(x, y, BTN_WIDTH, BTN_HEIGHT);
    this.onClick = onClick;
  }

  public void draw(Graphics2D g) {
    g.setColor(Color.decode("#fafafa"));
    g.fill(r);
    g.setColor(Color.decode("#4a4a4a"));
    Utils.drawTextCenterInRect(g, r, text, "h3");
  }

  public void handleClick(Point point) {
    if (r.contains(point)) {
      this.onClick.run();
    }
  }

  public boolean contains(Point point) {
    return r.contains(point);
  }
}
