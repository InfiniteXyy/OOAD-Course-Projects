package gui.view.components;

import gui.common.GlobalColor;
import gui.common.GlobalFont;
import gui.store.Action;
import gui.store.Store;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameControlPanel extends JPanel {

  private JButton continueDrawBtn;
  private JButton quitDrawBtn;
  private JButton doubleBtn;
  private Store store = Store.getInstance();
  private JLabel label;


  public GameControlPanel() {
    setBackground(GlobalColor.primaryColor);
    setLayout(new GridBagLayout());

    continueDrawBtn = new JButton("抽牌");
    continueDrawBtn.addActionListener(e -> store.dispatch(Action.game("ADD_MY_CARD")));
    quitDrawBtn = new JButton("放弃");
    quitDrawBtn.addActionListener(e -> store.dispatch(Action.game("QUIT_DRAW")));
    doubleBtn = new JButton("加倍");
    doubleBtn.addActionListener(e -> store.dispatch(Action.game("DOUBLE_MONEY")));
    label = new JLabel("已经放弃抽牌");
    label.setFont(GlobalFont.H2());
    label.setForeground(GlobalColor.white);
    addComponents();
    store.subscribe(Action.TYPE_GAMING, () -> {
      this.removeAll();
      addComponents();
      this.revalidate();
      this.repaint();
    });
    store.subscribe(Action.TYPE_SWITCH, () -> {
      this.removeAll();
      addComponents();
      this.revalidate();
      this.repaint();
    });
  }

  private void addComponents() {
    GridBagConstraints c = new GridBagConstraints();

    if (store.state.getCurPlayer() == null || store.state.getCurPlayer().isDrawing()) {
      c.fill = GridBagConstraints.BOTH;
      c.gridx = 0;
      c.gridy = 0;
      c.weightx = 0.5;
      c.weighty = 0.5;
      c.insets = new Insets(20, 16, 20, 16);
      add(continueDrawBtn, c);
      c.gridx = 1;
      c.gridy = 0;
      add(quitDrawBtn, c);
      c.gridx = 2;
      c.gridy = 0;
      add(doubleBtn, c);
    } else {
      c.insets = new Insets(20, 16, 20, 16);
      c.gridx = 0;
      c.gridy = 0;
      if (store.state.getCurPlayer().getCardSum() <= 21) {
        label.setText("已经放弃抽牌");
        label.setForeground(GlobalColor.white);
      } else {
        label.setText("玩家已经爆牌");
        label.setForeground(GlobalColor.red);
      }
      add(label, c);
    }

  }

}
