package w06.menuDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuDemo extends JFrame implements ActionListener {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    private JPanel redPanel;
    private JPanel whitePanel;
    private JPanel bluePanel;

    public static void main(String[] args){
        MenuDemo gui = new MenuDemo( );
        gui.setVisible(true);
    }

    public MenuDemo( ){
        super("Menu Demonstration");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3));

        //panels
        redPanel = new JPanel( );
        redPanel.setBackground(Color.LIGHT_GRAY);
        add(redPanel);

        whitePanel = new JPanel( );
        whitePanel.setBackground(Color.LIGHT_GRAY);
        add(whitePanel);

        bluePanel = new JPanel( );
        bluePanel.setBackground(Color.LIGHT_GRAY);
        add(bluePanel);

        //menus
        JMenu colorMenu = new JMenu("Add Colors");

        JMenuItem redChoice = new JMenuItem("Red"); //see Savitch p972-973 for JButton text and actionCommand relationship
        redChoice.addActionListener(this);
        colorMenu.add(redChoice);

        JMenuItem whiteChoice = new JMenuItem("White");
        whiteChoice.addActionListener(this);
        colorMenu.add(whiteChoice);

        JMenuItem blueChoice = new JMenuItem("Blue");
        blueChoice.addActionListener(this);
        colorMenu.add(blueChoice);

        JMenuBar bar = new JMenuBar( );
        bar.add(colorMenu);
        setJMenuBar(bar);
    }

    public void actionPerformed(ActionEvent e){
        String buttonCommandString = e.getActionCommand( );

        if (buttonCommandString.equals("Red"))
             redPanel.setBackground(Color.RED);
        else if (buttonCommandString.equals("White"))
            whitePanel.setBackground(Color.WHITE);
        else if (buttonCommandString.equals("Blue"))
            bluePanel.setBackground(Color.BLUE);
        else
            System.out.println("Unexpected error.");
    }
}
