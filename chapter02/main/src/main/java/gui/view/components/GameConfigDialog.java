package gui.view.components;

import gui.store.Action;
import gui.store.Store;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class GameConfigDialog extends JDialog {

  private JPanel contentPane;
  private JButton buttonOK;
  private JButton buttonCancel;
  private JTextField textField1;

  public GameConfigDialog() {
    setContentPane(contentPane);
    setModal(true);
    getRootPane().setDefaultButton(buttonOK);
    setLocationRelativeTo(null);
    setResizable(false);
    textField1.setText("3");

    buttonOK.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onOK();
      }
    });

    buttonCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onCancel();
      }
    });

    // call onCancel() when cross is clicked
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onCancel();
      }
    });

    // call onCancel() on ESCAPE
    contentPane.registerKeyboardAction(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                           onCancel();
                                         }
                                       }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
  }

  private void onOK() {
    try {
      int num = Integer.parseInt(textField1.getText());
      if (num < 0 || num > 10) {
        throw new Exception();
      }
      Store.getInstance().dispatch(new Action(Action.TYPE_STAGE, "START_GAME", num));
    } catch (Exception e) {
      System.out.println("Wrong number");
    } finally {
      dispose();
    }
  }

  private void onCancel() {
    // add your code here if necessary
    dispose();
  }

  public static void main(String[] args) {
    GameConfigDialog dialog = new GameConfigDialog();
    dialog.pack();
    dialog.setVisible(true);
    System.exit(0);
  }

  public static void createDialog() {
    GameConfigDialog dialog = new GameConfigDialog();
    dialog.pack();
    dialog.setVisible(true);
  }
}
