package gui.store;

import game.ComputerPlayer;
import game.Game;
import game.Player;
import gui.view.components.ConfigDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

// game.Game 类即为 reducer
public class Store {

  private static Store store = new Store();
  private Map<String, List<Runnable>> listeners;

  public Store() {
    this.listeners = new HashMap<>();
    this.listeners.put(Action.TYPE_STAGE, new ArrayList<>());
    this.listeners.put(Action.TYPE_GAMING, new ArrayList<>());
    this.listeners.put(Action.TYPE_ALL, new ArrayList<>());
    this.listeners.put(Action.TYPE_SWITCH, new ArrayList<>());
    this.state = new State();
  }

  public static Store getInstance() {
    return store;
  }

  public State state;


  public Runnable subscribe(String type, Runnable listener) {
    listeners.get(type).add(listener);
    return () -> this.listeners.get(type).remove(listener);
  }


  public void dispatch(Action action) {
    // System.out.println(action);
    switch (action.type) {
      case Action.TYPE_GAMING:
        handleGameActions(action);
        break;
      case Action.TYPE_STAGE:
        handleStageActions(action);
        break;
      case Action.TYPE_SWITCH:
        handleSwitchActions(action);
        break;
    }
    for (Runnable listener : listeners.get(Action.TYPE_ALL)) {
      listener.run();
    }
  }

  private void handleSwitchActions(Action action) {
    switch (action.action) {
      case "SET_PLAYER":
        this.state.curPlayerIndex = action.optionalInt;
        break;
    }
    for (Runnable listener : listeners.get(Action.TYPE_SWITCH)) {
      listener.run();
    }
  }

  private void handleStageActions(Action action) {
    if (action == null) {
      for (Runnable listener : listeners.get(Action.TYPE_STAGE)) {
        listener.run();
      }
      return;
    }
    switch (action.action) {
      case "CONFIG_GAME":
        ConfigDialog.createDialog();
        break;
      case "START_GAME":
        state.hasConfiged = true;
        this.state.players = new ArrayList<>();
        for (int i = 0; i < action.optionalInt; i++) {
          this.state.players.add(new Player());
        }
        break;
      case "GAME_AGAIN":
        this.state.winner = null;
        this.state.game = Game.Companion.createGame(this.state.players);
        for (Player p : this.state.players) {
          boolean hasSet = false;
          while (!hasSet) {
            try {
              String text = JOptionPane.showInputDialog(null, "玩家" + p.getUserId() + "输入赌注", "3");
              int num = Integer.parseInt(text);
              if (num < 0 || num > p.getMoney()) {
                JOptionPane.showMessageDialog(null, "余额不足");
                hasSet = false;
              } else {
                hasSet = true;
                p.setMoneyOnDesk(num);
              }
            } catch (Exception e) {
              return;
            }
          }

        }
        this.state.stage = State.STAGE_PLAYING;
        for (Player player : this.state.players) {
          player.reset();
        }
        dispatch(new Action(Action.TYPE_SWITCH, "SET_PLAYER", 0));
        break;
      case "END_GAME":
        this.state.game = null;
        this.state.stage = State.STAGE_READY;
        break;
      default:
        break;
    }
    for (Runnable listener : listeners.get(Action.TYPE_STAGE)) {
      listener.run();
    }

  }

  private void handleGameActions(Action action) {
    Game game = this.state.game;
    Player player = this.state.players.get(this.state.curPlayerIndex);
    switch (action.action) {
      case "ADD_MY_CARD":
        if (player.getCardSum() > 21) {
          player.setStopDrawing();
          handleGameActions(Action.game("QUIT_DRAW"));
        } else {
          game.drawCardForPlayer(player);
        }
        break;
      case "QUIT_DRAW":
        player.setStopDrawing();
        if (this.state.getNextPlayer() == -1) {
          this.state.stage = State.STAGE_GAME_RESULT;
          handleStageActions(null);
        } else {
          dispatch(new Action(Action.TYPE_SWITCH, "SET_PLAYER", this.state.getNextPlayer()));
        }
        if (state.judgeGame()) {
          JOptionPane.showMessageDialog(null,
              "胜利者是：玩家" + (state.winner instanceof ComputerPlayer ? "电脑"
                  : state.winner.getUserId()),
              "结果", JOptionPane.PLAIN_MESSAGE);
        }
        break;
      case "DOUBLE_MONEY":
        int money = player.getMoneyOnDesk();
        if (player.getMoney() < 2 * money) {
          JOptionPane.showMessageDialog(null, "余额不足");
        } else {
          player.setMoneyOnDesk(2 * player.getMoneyOnDesk());
        }
      default:
        break;
    }
    for (Runnable listener : listeners.get(Action.TYPE_GAMING)) {
      listener.run();
    }

  }
}
