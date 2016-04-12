package w06.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 A simplified calculator. 
 The only operations are addition and subtraction.
*/
public class Calculator extends JFrame implements ActionListener {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 200;
    public static final int NUMBER_OF_DIGITS = 30;

    private JTextField ioField;
    private double result = 0.0;

    public static void main(String[] args)     {
        Calculator aCalculator = new Calculator( );
        aCalculator.setVisible(true);
    }

    public Calculator( ) {
        setTitle("Simplified Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout( ));

        //text field for numbers @ north
        JPanel textPanel = new JPanel( );
        textPanel.setLayout(new FlowLayout( ));
        ioField = new JTextField("Enter numbers here.", NUMBER_OF_DIGITS);
        ioField.setBackground(Color.WHITE);
        textPanel.add(ioField);
        add(textPanel, BorderLayout.NORTH);

        //+/- and Reset buttons @ center
        JPanel buttonPanel = new JPanel( );
        buttonPanel.setBackground(Color.BLUE);
        buttonPanel.setLayout(new FlowLayout( ));

        JButton addButton = new JButton("+");
        addButton.addActionListener(this);
        buttonPanel.add(addButton); 
        JButton subtractButton = new JButton("-");
        subtractButton.addActionListener(this);
        buttonPanel.add(subtractButton); 
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

       add(buttonPanel, BorderLayout.CENTER);
    }


    public void actionPerformed(ActionEvent e) { // click event handler for all 3 buttons for all 3 buttons
        //GUI will not crash if an exception is thrown but not caught
        //but the state of an application may be inconsistent with its GUI if you don't catch and deal with a thrown exception
        try{
            assumingCorrectNumberFormats(e);
        }
        catch (NumberFormatException e2){ //e2 because e already declared in this scope, remember formal params are local variables
            ioField.setText("Error: Reenter Number.");  //report error, running total unchanged
        }
    }


    //Potentially Throws NumberFormatException.
    public void assumingCorrectNumberFormats(ActionEvent e){
        String actionCommand = e.getActionCommand( );

        if (actionCommand.equals("+")){
            result = result + stringToDouble(ioField.getText( ));   //if stringToDouble throws an exception the statement will not complete
            ioField.setText(Double.toString(result));               //and running total will not be corrupted
        }
        else if (actionCommand.equals("-")){
            result = result - stringToDouble(ioField.getText( ));   //  ditto
            ioField.setText(Double.toString(result));               //  ditto

        }
        else if (actionCommand.equals("Reset")){
            result = 0.0;
            ioField.setText("0.0");
        }
        else
            ioField.setText("Unexpected error.");
     }


    //Potentially Throws NumberFormatException.
    private static double stringToDouble(String stringObject){
        return Double.parseDouble(stringObject.trim());
    }

}
