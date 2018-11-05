package gui.view.components;

import gui.store.Action;
import gui.store.Store;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ConfigDialog extends JDialog {

  private JPanel contentPane;
  private JButton buttonOK;
  private JButton buttonCancel;
  private JTextField textField1;

  public ConfigDialog() {
    contentPane = new JPanel();
    contentPane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    buttonOK = new JButton("确定");
    buttonCancel = new JButton("取消");
    JLabel label = new JLabel("请输入玩家数量（1~10）");
    textField1 = new JTextField();
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1.0;
    c.weighty = 0.5;
    c.gridwidth = 2;
    contentPane.add(label, c);
    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 1.0;
    c.weighty = 0.5;
    c.gridwidth = 2;
    contentPane.add(textField1, c);
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 1;
    contentPane.add(buttonOK, c);
    c.gridx = 1;
    c.gridy = 2;
    c.gridwidth = 1;
    contentPane.add(buttonCancel, c);


    setContentPane(contentPane);
    setModal(true);
    getRootPane().setDefaultButton(buttonOK);
    setLocationRelativeTo(null);
    setResizable(false);
    textField1.setText("3");

    buttonOK.addActionListener(e -> onOK());

    buttonCancel.addActionListener(e -> onCancel());

    // call onCancel() when cross is clicked
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onCancel();
      }
    });

    // call onCancel() on ESCAPE
    contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
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
    dispose();
  }

  public static void createDialog() {
    ConfigDialog dialog = new ConfigDialog();
    dialog.pack();
    dialog.setVisible(true);
  }

}
