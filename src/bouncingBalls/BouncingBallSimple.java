package bouncingBalls;

import javax.swing.*;
import java.awt.*;
import java.util.Formatter;

/**
 * One ball bouncing inside a rectangular box.
 * All codes in one file. Poor design!
 */
// Extends JPanel, so as to override the paintComponent() for custom rendering codes.
public class BouncingBallSimple extends JPanel {
    // Container box's width and height
    private static final int BOX_WIDTH = 640;
    private static final int BOX_HEIGHT = 480;

    // bouncingBalls.Ball's properties
    private float ballRadius = 20; // bouncingBalls.Ball's radius
    private float ballX = ballRadius + 50; // bouncingBalls.Ball's center (x, y)
    private float ballY = ballRadius + 20;
    private float ballSpeedX = 15;   // bouncingBalls.Ball's speed for x and y
    private float ballSpeedY = 10;

    private static final int UPDATE_RATE = 30; // Number of refresh per second

    /** Constructor to create the UI components and init game objects. */
    public BouncingBallSimple() {
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));

        // Start the ball bouncing (in its own thread)
        Thread gameThread = new Thread() {
            public void run() {
                while (true) { // Execute one update step (animation frame calculation) per loop
                    // Calculate the ball's new position
                    ballX += ballSpeedX;
                    ballY += ballSpeedY;
                    // Check if the ball moves over the x bounds
                    // If so, adjust the position and speed.
                    if (ballX - ballRadius < 0) {
                        ballSpeedX = -ballSpeedX; // Reflect along normal (i.e. reflect x component of velocity)
                        ballX = ballRadius; // Re-position the ball at the edge
                    } else if (ballX + ballRadius > BOX_WIDTH) {
                        ballSpeedX = -ballSpeedX;
                        ballX = BOX_WIDTH - ballRadius;
                    }

                    // Check if the ball moves over the y bounds
                    if (ballY - ballRadius < 0) {
                        ballSpeedY = -ballSpeedY;
                        ballY = ballRadius;
                    } else if (ballY + ballRadius > BOX_HEIGHT) {
                        ballSpeedY = -ballSpeedY;
                        ballY = BOX_HEIGHT - ballRadius;
                    }
                    // Refresh the display based on new calculations (java will decide exactly when)
                    repaint(); // Callback paintComponent()

                    // Delay for timing control and give other threads a chance
                    try {
                        Thread.sleep(1000 / UPDATE_RATE);  // milliseconds
                    }
                    catch (InterruptedException ex) { } //the exception must be handled (java enforces)
                }
            }
        }; //this ends the declaration and instantiation of the game thread object

        gameThread.start();  // Callback run() (kicks of the game loop)
    }

    /** Custom rendering codes for drawing the JPanel */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // Paint background

        // Draw the box
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

        // Draw the ball
        g.setColor(Color.BLUE);
        // draw ball centred on (ballX, ballY) not (ballX, ballY) = top left hand corner of containing box
        g.fillOval((int) (ballX - ballRadius), (int) (ballY - ballRadius),
                (int)(2 * ballRadius), (int)(2 * ballRadius));

        // Display the ball's information
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.PLAIN, 12));
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("bouncingBalls.Ball @(%3.0f,%3.0f) Speed=(%2.0f,%2.0f)", ballX, ballY, ballSpeedX, ballSpeedY);
        g.drawString(sb.toString(), 20, 30);
    }

    /** main program (entry point) */
    public static void main(String[] args) {
        // Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Set up main window (using Swing's Jframe)
                JFrame frame = new JFrame("A Bouncing bouncingBalls.Ball (NOT OOP)");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //only way to kill the game loop
                frame.setContentPane(new BouncingBallSimple());
                frame.pack(); //shrink JFrame to the JPanel it contains
                frame.setVisible(true);
            }
        });
    }
}
