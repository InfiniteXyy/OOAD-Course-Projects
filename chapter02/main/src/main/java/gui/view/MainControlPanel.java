package gui.view;

import game.Player;
import gui.common.GlobalFont;
import gui.store.Action;
import gui.store.State;
import gui.store.Store;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainControlPanel extends JPanel {

  JButton mainButton;
  JButton moneyButton;
  private boolean hasConfiged = false;

  public MainControlPanel() {
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    JLabel logo = new JLabel("21 GAME", SwingConstants.CENTER);
    logo.setFont(GlobalFont.H2());
    c.fill = GridBagConstraints.BOTH;
    c.weighty = 1;
    c.weightx = 0.5;
    c.gridx = 0;
    c.gridy = 0;
    add(logo, c);

    State state = Store.getInstance().state;
    mainButton = new JButton("初始化");
    moneyButton = new JButton("查看金钱");
    moneyButton.setVisible(false);

    Store.getInstance().subscribe(Action.TYPE_STAGE, () -> {
      mainButton.setText(state.stage.equals(State.STAGE_READY) ? "再来一轮" : "结束");
      moneyButton.setVisible(true);
    });

    moneyButton.addActionListener(e -> {
      StringBuilder message = new StringBuilder();
      for (Player player : Store.getInstance().state.players) {
        message.append("玩家").append(player.getUserId()).append("：余额：").append(player.getMoney())
            .append("\n");
      }
      JOptionPane.showMessageDialog(null, message.toString(), "金钱", JOptionPane.PLAIN_MESSAGE);
    });

    mainButton.addActionListener(e -> {
      if (state.stage.equals(State.STAGE_READY)) {
        Store.getInstance().dispatch(Action.stage(!hasConfiged ? "CONFIG_GAME" : "GAME_AGAIN"));
        if (!hasConfiged) {
          hasConfiged = true;
        }
      } else {
        Store.getInstance().dispatch(Action.stage("END_GAME"));
      }
    });
    c.weighty = 1;
    c.weightx = 0.5;
    c.gridx = 2;
    c.gridy = 0;
    add(mainButton, c);
    c.weighty = 1;
    c.weightx = 0.5;
    c.gridx = 1;
    c.gridy = 0;
    add(moneyButton, c);
  }

}
