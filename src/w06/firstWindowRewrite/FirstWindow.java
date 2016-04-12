package w06.firstWindowRewrite;

import javax.swing.*;

public class FirstWindow extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    public FirstWindow(){
        super(); //JFrame initialisation

        setSize(WIDTH,HEIGHT);
        setTitle("First Window Class");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JButton endButton = new JButton("Click to end program.");

        //ASSIGNING LISTENER OBJECTS

        //SYNTAX 1 - Named listener Class, Named listener object
        //straightforward and familiar
        EndButtonClickListener clickListener = new EndButtonClickListener();
        endButton.addActionListener(clickListener); //must add an "ActionListener" object
                                                    //i.e. an instance of a class that implements
        add(endButton);                             //the "ActionListener" interface
    }
}

