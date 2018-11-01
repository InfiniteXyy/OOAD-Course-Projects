package gui.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class ReadyView extends JPanel {

  public ReadyView() {
    setBackground(Color.decode("#44617b"));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.WHITE);
    Utils.drawTextCenterInRect(g2, g2.getClipBounds(), "按「开始游戏」启动", "h1");
  }
}
