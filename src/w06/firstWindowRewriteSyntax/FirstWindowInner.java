package w06.firstWindowRewriteSyntax;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindowInner extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    private class EndButtonClickListener2 implements ActionListener {

        public void actionPerformed(ActionEvent e){

            System.exit(0); //exit normally
        }
    }

    public FirstWindowInner(){
        super(); //JFrame initialisation

        setSize(WIDTH,HEIGHT);
        setTitle("First Window Class");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JButton endButton = new JButton("Click to end program.");

        //ASSIGNING LISTENER OBJECTS

        //SYNTAX 1 - Named listener Class, Named listener object
        //straightforward and familiar
        EndButtonClickListener2 clickListener = new EndButtonClickListener2();
        endButton.addActionListener(clickListener);

        //SYNTAX 2 - Named listener Class, Anonymous listener object
        //new syntax: objects can be instantiated in situ (in this case as a parameter)
        //the object is anonymous, no name is needed in this context so no problem
        endButton.addActionListener(new EndButtonClickListener());

        //SYNTAX 3 - Anonymous inner listener class, Anonymous listener object
        //new syntax: classes can be nested inside other classes
        //(what is the scope of outer class members wrt inner classes?)
        //new syntax: classes can be defined anonymously
        //(in addition to objects being declared anonymously)
        //NOT USING EndButtonClickListener class
        //NOW REQUIRE ITS IMPORT STATEMENTS HERE IN FirstWindow
        endButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0); //exit normally
            }
        });


        add(endButton);
    }
}








