package w06.firstWindowRewriteSyntax;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        //NOT USING ENDBUTTONCLICKLISTENER
        //NOW REQUIRE ITS IMPORT STATEMENTS HERE IN FIRSTWINDOW
        endButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0); //exit normally
            }
        });

        //SYNTAX 4 - Using Inner Classes
        //(classes can be nested, interfaces can be nested, interfaces can be nested inside classes)
        //Why? good code organisation e.g. if this is the only class to use this inner class why not nest them!
        //see FirstWindowInner


        add(endButton);
    }
}

