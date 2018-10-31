package gui.view;

import gui.store.Store;
import gui.view.components.Button;
import gui.view.components.CardView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JPanel;

public class GameDesk extends JPanel {

  private Button continueDrawBtn;
  private Button quitDrawBtn;
  private Runnable unsubscribe;
  private Store store = Store.getInstance();

  public GameDesk() {
    continueDrawBtn = new Button(16, 400, "抽牌", () -> {
      store.dispatch("ADD_MY_CARD");
    });
    quitDrawBtn = new Button(132, 400, "放弃", () -> {
      store.dispatch("ADD_COMPUTER_CARD");

    });
    setBackground(Color.decode("#44617b"));
    addButtonClickListener();
    unsubscribe = store.subscribe(this::repaint);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!store.state.gameRunning) {
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.WHITE);
      Utils.drawTextCenterInRect(g2, g2.getClipBounds(), "按「开始游戏」启动", "h1");
      return;
    }
    List<CardView> computerCards = new ArrayList<>();
    List<CardView> myCards = new ArrayList<>();
    List<Integer> stateMe = store.state.myCards;
    List<Integer> stateYou = store.state.yourCards;
    for (int i = 0; i < stateYou.size(); i++) {
      computerCards.add(new CardView(i, 0, stateYou.get(i)));
    }
    for (int i = 0; i < stateMe.size(); i++) {
      myCards.add(new CardView(i, 1, stateMe.get(i)));
    }

    Graphics2D g2 = (Graphics2D) g;
    for (CardView cardView : computerCards) {
      cardView.draw(g2);
    }
    for (CardView cardView : myCards) {
      cardView.draw(g2);
    }
    continueDrawBtn.draw(g2);
    quitDrawBtn.draw(g2);
  }

  private void addButtonClickListener() {
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        continueDrawBtn.handleClick(e.getPoint());
        quitDrawBtn.handleClick(e.getPoint());
      }
    });

    addMouseMotionListener(new MouseMotionListener() {
      @Override
      public void mouseDragged(MouseEvent e) {

      }

      @Override
      public void mouseMoved(MouseEvent e) {
        if (store.state.gameRunning) {
          if (continueDrawBtn.contains(e.getPoint()) || quitDrawBtn.contains(e.getPoint())) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
          } else {
            setCursor(Cursor.getDefaultCursor());
          }
        }
      }
    });
  }
}
