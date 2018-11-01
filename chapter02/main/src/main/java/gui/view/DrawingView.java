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
import javax.swing.JPanel;

public class DrawingView extends JPanel {

  private Button continueDrawBtn;
  private Button quitDrawBtn;
  private Store store = Store.getInstance();

  public DrawingView() {
    setBackground(Color.decode("#44617b"));
    continueDrawBtn = new Button(16, 400, "抽牌", () -> {
      store.dispatch("ADD_MY_CARD");
    });
    quitDrawBtn = new Button(132, 400, "放弃", () -> {
      store.dispatch("QUIT_DRAW");
    });
    addButtonClickListener();
  }

  private void addButtonClickListener() {
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        continueDrawBtn.handleClick(e.getPoint());
        quitDrawBtn.handleClick(e.getPoint());
      }
    });
    this.addMouseMotionListener(new MouseMotionListener() {
      @Override
      public void mouseDragged(MouseEvent e) {

      }

      @Override
      public void mouseMoved(MouseEvent e) {
        if (continueDrawBtn.contains(e.getPoint()) || quitDrawBtn.contains(e.getPoint())) {
          setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
          setCursor(Cursor.getDefaultCursor());
        }
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    List<CardView> myCards = new ArrayList<>();
    List<Integer> stateMe = store.state.game.getCurrentPlayer().getCards();
    for (int i = 0; i < stateMe.size(); i++) {
      myCards.add(new CardView(i, 1, stateMe.get(i)));
    }
    Graphics2D g2 = (Graphics2D) g;
    for (CardView cardView : myCards) {
      cardView.draw(g2);
    }
    continueDrawBtn.draw(g2);
    quitDrawBtn.draw(g2);
  }
}
