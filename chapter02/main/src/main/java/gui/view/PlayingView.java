package gui.view;

import gui.store.Action;
import gui.store.Store;
import gui.view.components.CardDrawingPanel;
import gui.view.components.GameControlPanel;
import gui.view.components.StatusPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class PlayingView extends JPanel {

  private CardDrawingPanel drawingPanel = new CardDrawingPanel();
  private GameControlPanel controlPanel = new GameControlPanel();
  private StatusPanel statusPanel = new StatusPanel();

  public PlayingView() {
    initLayout();
    Store store = Store.getInstance();
    store.subscribe(Action.TYPE_SWITCH, drawingPanel::repaint);
    store.subscribe(Action.TYPE_GAMING, drawingPanel::repaint);
  }

  private void initLayout() {
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 0.8;
    c.weighty = 0.95;
    add(drawingPanel, c);
    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 0.2;
    c.weighty = 1;
    c.gridheight = 2;
    add(statusPanel, c);
    c.gridx = 0;
    c.gridy = 1;
    c.weighty = 0.05;
    c.gridheight = 1;
    add(controlPanel, c);
  }
}
