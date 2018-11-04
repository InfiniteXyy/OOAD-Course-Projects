package gui.view.components;

import game.Player;
import gui.common.GlobalFont;
import gui.store.Action;
import gui.store.Store;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class StatusPanel extends JPanel {

  private Store store = Store.getInstance();

  public StatusPanel() {
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    JLabel info = new JLabel("游戏中", SwingConstants.CENTER);

    info.setFont(GlobalFont.get("h3"));
    store.subscribe(Action.TYPE_ALL, () -> {
      String s;
      if (store.state.winner == null) {
        s = "游戏中";
      } else {
        s = "恭喜玩家" + store.state.winner.getUserId() + "！";
      }
      info.setText(s);
    });

    JList<Object> playerJList = new JList<>(store.state.players.toArray());
    store.subscribe(Action.TYPE_SWITCH, () -> {
      if (store.state.players != null) {
        playerJList.setListData(store.state.players.toArray());
        playerJList.setSelectedIndex(store.state.curPlayerIndex);
      }
    });
    playerJList.setCellRenderer(new PlayerCellRenderer());

    playerJList.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        JList list = (JList) e.getSource();
        int index = list.locationToIndex(e.getPoint());
        store.dispatch(new Action(Action.TYPE_SWITCH, "SET_PLAYER", index));
      }
    });
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.weighty = 0.3;
    c.weightx = 1.0;
    add(info, c);
    c.gridy = 1;
    c.weightx = 1.0;
    c.weighty = 0.7;
    add(playerJList, c);
  }

  class PlayerCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
        boolean isSelected, boolean cellHasFocus) {
      if (value != null) {
        Player player = (Player) value;
        value = (String.format("玩家%d", player.getUserId()));
        if (store.state.winner != null) {
          value += " 奖金 + " + store.state.deltaMoney[index];
        }
        this.setAlignmentX(0.5f);
      }
      return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }


  }
}
