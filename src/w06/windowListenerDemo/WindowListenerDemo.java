package w06.windowListenerDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// 2 JFRAMES - application window + dialog window

//OUTER CLASS (APPLICATION WINDOW)
public class WindowListenerDemo extends JFrame { //this JFrame references the application window
    public static final int WIDTH = 300; //for main window
    public static final int HEIGHT = 200; //for main window
    public static final int SMALL_WIDTH = 200; //for confirm window
    public static final int SMALL_HEIGHT = 100;//for confirm window


    //INNER CLASS (WINDOW EVENT LISTENER)
    private class CheckOnExit implements WindowListener { //7 methods to implement, 1 really and 6 null in this case
        public void windowOpened(WindowEvent e)
        {}

        public void windowClosing(WindowEvent e){
            ConfirmWindow checkers = new ConfirmWindow( );
            checkers.setVisible(true);
        }

        public void windowClosed(WindowEvent e){}

        public void windowIconified(WindowEvent e){}

         public void windowDeiconified(WindowEvent e){}

        public void windowActivated(WindowEvent e){}

        public void windowDeactivated(WindowEvent e){}
    } //End of inner class CheckOnExit



    //INNER CLASS (YES/NO DIALOG WINDOW)
    private class ConfirmWindow extends JFrame implements ActionListener { //this JFrame references the Yes/No dialogue window
        public ConfirmWindow( ){
            setSize(SMALL_WIDTH, SMALL_HEIGHT);
            getContentPane( ).setBackground(Color.YELLOW);
            setLayout(new BorderLayout( ));

            JLabel confirmLabel = new JLabel("Are you sure you want to exit?");
            add(confirmLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel( );
            buttonPanel.setBackground(Color.ORANGE);
            buttonPanel.setLayout(new FlowLayout( ));

            JButton exitButton = new JButton("Yes");
            exitButton.addActionListener(this);
            buttonPanel.add(exitButton);

            JButton cancelButton = new JButton("No");
            cancelButton.addActionListener(this);
            buttonPanel.add(cancelButton);

            add(buttonPanel, BorderLayout.SOUTH);
        }

        public void actionPerformed(ActionEvent e){
            String actionCommand = e.getActionCommand( );

            if (actionCommand.equals("Yes")) 
                System.exit(0); //will dispose of both windows and end the application
            else if (actionCommand.equals("No"))
                dispose( );//Destroys (garbage collects) only the ConfirmWindow and all that it contains.
            else
                System.out.println("Unexpected Error in Confirm Window.");
        }
    } //End of inner class ConfirmWindow



    public static void main(String[] args){
        WindowListenerDemo demoWindow = new WindowListenerDemo( );
        demoWindow.setVisible(true);
    }

    public WindowListenerDemo( ){
        setSize(WIDTH, HEIGHT);
        setTitle("Window Listener Demonstration");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //so only our window event listener will do anything
        addWindowListener(new CheckOnExit( )); //in situ instantiation of an anonymous window listener object

        getContentPane( ).setBackground(Color.LIGHT_GRAY);
        JLabel aLabel = new JLabel("I like to be sure you are sincere.");
        add(aLabel);
    }
}
