package w06.panelDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelDemo extends JFrame implements ActionListener {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    private JPanel redPanel;
    private JPanel whitePanel;
    private JPanel bluePanel;

    public static void main(String[] args){
        PanelDemo gui = new PanelDemo( );
        gui.setVisible(true);
    }

    public PanelDemo( ){
        super("Panel Demonstration");

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //JPanel has a border layout manager
        setLayout(new BorderLayout( ));

        JPanel colourPanelsContainer = new JPanel( );
        colourPanelsContainer.setLayout(new GridLayout(1, 3)); //1 row 3 columns - 3 cells 1 each for a colour panel

        // instantiate colour panels amd pack them into a parent panel container (colourPanelsContainer)
        redPanel = new JPanel( );
        redPanel.setBackground(Color.LIGHT_GRAY);
        colourPanelsContainer.add(redPanel);

        whitePanel = new JPanel( );
        whitePanel.setBackground(Color.LIGHT_GRAY);
        colourPanelsContainer.add(whitePanel);

        bluePanel = new JPanel( );
        bluePanel.setBackground(Color.LIGHT_GRAY);
        colourPanelsContainer.add(bluePanel);

        //add the parent panel container to the centre of the JFrame's layout
        add(colourPanelsContainer, BorderLayout.CENTER);


        //create another parent panel (buttonPanel) with a flow layout manager
        JPanel buttonPanel = new JPanel( );
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setLayout(new FlowLayout( ));

        //instantiate 3 buttons, assign action listener to each, pack them into the parent panel
        JButton redButton = new JButton("Red");
        redButton.setBackground(Color.RED);
        redButton.addActionListener(this);
        buttonPanel.add(redButton);

        JButton whiteButton = new JButton("White");
        whiteButton.setBackground(Color.WHITE);
        whiteButton.addActionListener(this);
        buttonPanel.add(whiteButton);

        JButton blueButton = new JButton("Blue");
        blueButton.setBackground(Color.BLUE);
        blueButton.addActionListener(this);
        buttonPanel.add(blueButton);

        //add parent panel container to the south (page end) of the JFrame's layout
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e){
        String buttonString = e.getActionCommand( ); //default action is button text???

        if (buttonString.equals("Red"))
            redPanel.setBackground(Color.RED);
        else if (buttonString.equals("White"))
            whitePanel.setBackground(Color.WHITE);
        else if (buttonString.equals("Blue"))
            bluePanel.setBackground(Color.BLUE);
        else
            System.out.println("Unexpected error.");
    }
}

