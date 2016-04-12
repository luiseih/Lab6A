package bouncingBalls;

import javax.swing.*;

/**
 * bouncingBalls.Main Program for running the bouncing ball as a standalone application.
 */
public class Main {
    // Entry main program
    public static void main(String[] args) {
        // Run UI in the Event Dispatcher Thread (EDT), instead of bouncingBalls.Main thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("OOP Bouncing bouncingBalls.Ball");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new BallWorld(640, 480)); // bouncingBalls.BallWorld is a JPanel
                frame.pack();            // Preferred size of bouncingBalls.BallWorld
                frame.setVisible(true);  // Show it
            }
        });
    }
}
