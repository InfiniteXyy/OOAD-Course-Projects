package gui.view.components;

import game.ComputerPlayer;
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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class StatusPanel extends JPanel {

  private Store store = Store.getInstance();

  public StatusPanel() {
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    JTextArea textInfo = new JTextArea(10, 9);
    JScrollPane scroller = new JScrollPane(textInfo);
    textInfo.setLineWrap(true);//启动自动换行
    textInfo.setEditable(false);
    textInfo.setFont(GlobalFont.get("h4"));
    store.subscribe(Action.TYPE_ALL, () -> {
      StringBuilder s;
      s = new StringBuilder("游戏中\n");
      if (store.state.game != null) {
        ComputerPlayer computer = store.state.game.getComputerPlayer();
        if (store.state.winner == null) {
          s.append(String.format("电脑：%s ...\n\n", computer.getCards()));
        } else {
          s.append(String.format("电脑：%s\n -> %d\n", computer.getAllCards(), computer.getCardSum()));
        }
        for (Player player : store.state.players) {
          s.append(String.format("玩家%d：%s\n", player.getUserId(), player.getCards()));
          if (!player.isDrawing()) {
            s.append(" -> ").append(player.getCardSum());
          }
          s.append("\n");
        }
        if (store.state.winner != null) {
          s.append("恭喜玩家").append(store.state.winner.getUserId()).append("！");
        }
      }
      textInfo.setText(s.toString());
    });

    JList<Object> playerJList = new JList<>(store.state.players.toArray());
    playerJList.setEnabled(false);
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
        if (store.state.winner != null) {
          super.mousePressed(e);
          JList list = (JList) e.getSource();
          int index = list.locationToIndex(e.getPoint());
          store.dispatch(new Action(Action.TYPE_SWITCH, "SET_PLAYER", index));
        }
      }
    });
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.weighty = 0.3;
    c.weightx = 1.0;
    add(scroller, c);
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
