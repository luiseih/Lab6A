package w06.menuDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuDemo extends JFrame {

  public static final int WIDTH = 300;
  public static final int HEIGHT = 200;

  public static JPanel redPanel;
  public static JPanel whitePanel;
  public static JPanel bluePanel;

    public MenuDemo() {
        super("Menu Demonstration");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3));

      //panels
      redPanel = new JPanel();
      redPanel.setBackground(Color.LIGHT_GRAY);
      add(redPanel);

      whitePanel = new JPanel();
      whitePanel.setBackground(Color.LIGHT_GRAY);
      add(whitePanel);

      bluePanel = new JPanel();
      bluePanel.setBackground(Color.LIGHT_GRAY);
      add(bluePanel);

        //menus
        final JMenu colorMenu = new JMenu("Add Colors");

        JMenuItem redChoice = new JMenuItem("Red");
      // Named Listener Class, Named Listener Object.
        InnerMenuListener.RedListener redListener = new InnerMenuListener()
            .new RedListener();
        redChoice.addActionListener(redListener);
        colorMenu.add(redChoice);

        JMenuItem whiteChoice = new JMenuItem("White");
      // Named Listener Class, Anonymous Listener Object.
        whiteChoice.addActionListener(new InnerMenuListener().new WhiteListener());
        colorMenu.add(whiteChoice);

        JMenuItem blueChoice = new JMenuItem("Blue");
      colorMenu.add(blueChoice);
      // Anonymous inner listener class, Anonymous Listener Object.
        blueChoice.addActionListener(new InnerMenuListener()
            .new BlueListener(){
          public void actionPerformed(ActionEvent e) {
            bluePanel.setBackground(Color.BLUE);
          }
        });

        JMenuBar bar = new JMenuBar();
        bar.add(colorMenu);
        setJMenuBar(bar);
    }
}
