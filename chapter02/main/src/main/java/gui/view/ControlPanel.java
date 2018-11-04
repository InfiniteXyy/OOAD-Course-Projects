package gui.view;

import gui.common.GlobalFont;
import gui.store.Action;
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
    JLabel logo = new JLabel("21 GAME", SwingConstants.CENTER);
    logo.setFont(GlobalFont.H2());
    c.fill = GridBagConstraints.BOTH;
    c.weighty = 1;
    c.weightx = 0.5;
    c.gridx = 0;
    c.gridy = 0;
    add(logo, c);

    State state = Store.getInstance().state;
    JButton startBtn = new JButton("开始");
    Store.getInstance().subscribe(Action.TYPE_STAGE,
        () -> startBtn.setText(!state.stage.equals(State.STAGE_READY) ? "结束" : "开始"));
    startBtn.addActionListener(e -> {
      if (state.stage.equals(State.STAGE_READY)) {
        Store.getInstance().dispatch(Action.stage("START_GAME"));
      } else {
        Store.getInstance().dispatch(Action.stage("END_GAME"));

      }
    });
    c.weighty = 1;
    c.weightx = 0.5;
    c.gridx = 1;
    c.gridy = 0;
    add(startBtn, c);
  }
}
