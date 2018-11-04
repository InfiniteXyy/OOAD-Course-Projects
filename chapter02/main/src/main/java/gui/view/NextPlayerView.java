package gui.view;

import gui.store.Action;
import gui.store.Store;
import gui.view.components.Button;
import javax.swing.JPanel;

public class NextPlayerView extends JPanel {

  private Button nextBtn;
  private Store store = Store.getInstance();

  public NextPlayerView() {
    nextBtn = new Button(16, 400, "下一回合", () -> store.dispatch(Action.game("NEXT_TURN")));
  }


}
