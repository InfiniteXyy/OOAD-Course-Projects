package gui.view;

import gui.store.State;
import gui.store.Store;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class GameDesk extends JPanel {

  private ReadyView readyView = new ReadyView();
  private PlayingView playingView = new PlayingView();

  private Store store = Store.getInstance();
  private GridBagConstraints c;

  private JPanel getMainView() {
    String stage = store.state.stage;
    if (stage.equals(State.STAGE_READY)) {
      return readyView;
    } else {
      return playingView;
    }
  }

  public GameDesk() {
    this.setLayout(new GridBagLayout());
    c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1.0;
    c.weighty = 1.0;
    c.gridx = 0;
    c.gridy = 0;
    add(getMainView(), c);
  }

  public void refreshStage() {
    this.removeAll();
    this.add(getMainView(), c);
    this.revalidate();
    this.repaint();
  }
}
