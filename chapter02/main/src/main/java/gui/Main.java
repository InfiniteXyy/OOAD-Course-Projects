package gui;

import gui.store.Action;
import gui.store.Store;
import gui.view.ControlPanel;
import gui.view.GameDesk;
import gui.view.components.GameConfigDialog;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {
  static GameConfigDialog dialog = new GameConfigDialog();

  private static void addComponentsToPane(Container pane) {
    pane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    GameDesk gameDesk = new GameDesk();
    Store.getInstance().subscribe(Action.TYPE_STAGE, gameDesk::refreshStage);
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1;
    c.weighty = 99;
    c.gridx = 0;
    c.gridy = 0;
    pane.add(gameDesk, c);
    ControlPanel controlPanel = new ControlPanel();
    c.fill = GridBagConstraints.BOTH;
    c.weighty = 1;
    c.gridx = 0;
    c.gridy = 1;
    pane.add(controlPanel, c);
  }

  private static void createAndShowGUI() {
    //Create and set up the window.
    JFrame frame = new JFrame("21 game");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    //Set up the content pane.
    addComponentsToPane(frame.getContentPane());

    //Display the window.
    frame.pack();
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setResizable(false);
  }

  public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    JFrame.setDefaultLookAndFeelDecorated(false);
    javax.swing.SwingUtilities.invokeLater(Main::createAndShowGUI);
  }
}
