package gui.view;

import gui.common.GlobalFont;
import gui.store.State;
import gui.store.Store;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ControlPanel extends JPanel {

  public ControlPanel() {
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    JLabel logo = new JLabel("21 game", SwingConstants.CENTER);
    logo.setFont(GlobalFont.H1());
    c.fill = GridBagConstraints.BOTH;
    c.weighty = 1;
    c.weightx = 0.5;
    c.gridx = 0;
    c.gridy = 0;
    add(logo, c);

    JButton startBtn = new JButton("开始");
    Store.getInstance().subscribe(() -> {
      startBtn.setText(!Store.getInstance()
          .state.stage.equals(State.STAGE_READY) ? "暂停" : "开始");
    });
    startBtn.addActionListener(e -> {
      Store.getInstance().dispatch("START_GAME");
    });
    c.weighty = 1;
    c.weightx = 0.5;
    c.gridx = 1;
    c.gridy = 0;
    add(startBtn, c);

    JButton breakBtn = new JButton("结束");
    breakBtn.addActionListener(e -> Store.getInstance().dispatch("END_GAME"));
    c.weighty = 1;
    c.weightx = 0.5;
    c.gridx = 2;
    c.gridy = 0;
    add(breakBtn, c);
  }
}
