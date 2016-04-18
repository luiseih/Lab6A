package w06.menuDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InnerMenuListener extends JFrame {

  public class RedListener implements ActionListener {

      public void actionPerformed(ActionEvent e) {
        MenuDemo.redPanel.setBackground(Color.RED);
      }
    }

    public class WhiteListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        MenuDemo.whitePanel.setBackground(Color.WHITE);
    }
  }

    public class BlueListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        MenuDemo.bluePanel.setBackground(Color.BLUE);
    }
  }
}
