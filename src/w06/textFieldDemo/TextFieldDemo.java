package w06.textFieldDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TextFieldDemo extends JFrame implements ActionListener {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 200;
    public static final int NUMBER_OF_CHAR = 30;

    private JTextField name;


    public static void main(String[] args) {
        TextFieldDemo gui = new TextFieldDemo( );
        gui.setVisible(true);
    }


    public TextFieldDemo( ) {
        super("Text Field Demo");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1)); //2 rows, 1 column (top row label and text field, bottom row click me and enter buttons)

        JPanel namePanel = new JPanel( );
        namePanel.setLayout(new BorderLayout( ));
        namePanel.setBackground(Color.WHITE);

        name = new JTextField(NUMBER_OF_CHAR);

        namePanel.add(name, BorderLayout.SOUTH);
        JLabel nameLabel = new JLabel("Enter your name here:");
        namePanel.add(nameLabel, BorderLayout.CENTER); //center takes all horizontal space, components are left justified by default

        add(namePanel);

        JPanel buttonPanel = new JPanel( );
        buttonPanel.setLayout(new FlowLayout( ));
        buttonPanel.setBackground(Color.PINK);
        JButton actionButton = new JButton("Click me");
        actionButton.addActionListener(this);
        buttonPanel.add(actionButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton); 

        add(buttonPanel);
    }


    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand( );

        if (actionCommand.equals("Click me"))
            name.setText("Hello " + name.getText( )); //parameter evaluated first to get existing text then text reset
        else if (actionCommand.equals("Clear"))
            name.setText("");
        else
            name.setText("Unexpected error.");
    } 

}
 