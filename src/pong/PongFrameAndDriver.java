package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// the following class contains main but in this main instantiates an instance of itself to get things going
// ideally driver classes should not instantiate themselves
// its also a subclass of JPanel which is a class from the javax.swing
public class PongFrameAndDriver extends JFrame {

    //constructor
	public PongFrameAndDriver() { //our JFrame created (since its constructor has been called) now set it up as required
		super(); //JFrame's constructor

        //set frame properties
        setTitle("Pong");
        setSize(250, 250);
        setResizable(false);
        setLocationRelativeTo(null); //centre in screen

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setContentPane(new JPanel()); //replace default contentPane with custom panel
        getContentPane().setLayout(new BorderLayout());

        final PongPanel table = new PongPanel(); //final because of closure in event handlers for key presses
        getContentPane().add(table, BorderLayout.CENTER);


		//create and set a listener object (with event handlers) to handle key presses in the court JPanel
		addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent evt) {
                table.keyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                table.keyReleased(evt);
            }
        });

	}

	public static void main(String[] args) {
        // create a runnable object from an anonymous class and submit it to be run on the background awt Event Dispatching Thread (EDT)
        // EDT = the UI thread, all GUI interaction must be performed on this thread (Java does not enforce but odd things can happen if you don't)
        // Java uses the EDT to process all events from the AWT GUI queue - i.e. let Java manage the GUI
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PongFrameAndDriver aPongFrameAndDriver = new PongFrameAndDriver(); // invokes the constructor in this class
                aPongFrameAndDriver.setVisible(true);
            }
        });
	}
}