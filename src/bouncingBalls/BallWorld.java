package bouncingBalls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

/**
 * The control logic and main display panel for game.
 */
public class BallWorld extends JPanel {
    private static final int UPDATE_RATE = 30;  // Frames per second (fps)

    private Ball ball;         // A single bouncing bouncingBalls.Ball's instance
    private ContainerBox box;  // The container rectangular box

    private DrawCanvas canvas; // Custom canvas for drawing the box/ball
    private int canvasWidth;
    private int canvasHeight;

    /**
     * Constructor to create the UI components and init the game objects.
     * Set the drawing canvas to fill the screen (given its width and height).
     *
     * @param width : screen width
     * @param height : screen height
     */
    public BallWorld(int width, int height) {

        canvasWidth = width;
        canvasHeight = height;

        // Init the ball at a random location (inside the box) and moveAngle
        Random rand = new Random();
        int radius = 200;
        int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10; //centre of ball is symmetrically kept radius (required) + 10 (arbitrary buffer) away from left and right hand side
        int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
        int speed = 5;
        int angleInDegree = rand.nextInt(360);
        ball = new Ball(x, y, radius, speed, angleInDegree, Color.BLUE);

        // Init the Container Box to fill the "canvas"
        box = new ContainerBox(0, 0, canvasWidth, canvasHeight, Color.BLACK, Color.WHITE);

        // Init the custom drawing panel for drawing the game
        canvas = new DrawCanvas();
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);  //this adds a panel to a panel
                                                // 1. best not to use JPanel's in-built containerPane as its not a JPanel but a container (container is a superclass of JPanel)
                                                // 2. he's anticipating adding more panels to the top panel e.g. a control panel
        // Handling window resize.
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component c = (Component)e.getSource();
                Dimension dim = c.getSize();
                canvasWidth = dim.width;
                canvasHeight = dim.height;
                // Adjust the bounds of the container to fill the window
                box.set(0, 0, canvasWidth, canvasHeight);
            }
        });

        // Start the ball bouncing
        gameStart();
    }

    /** Start the ball bouncing. */
    public void gameStart() {
        // Run the game logic in its own thread.
        Thread gameThread = new Thread() {
            public void run() {
                while (true) {
                    // Execute one time-step for the game
                    gameUpdate();
                    // Refresh the display
                    repaint();
                    // Delay and give other thread a chance
                    try {
                        Thread.sleep(1000 / UPDATE_RATE);
                    } catch (InterruptedException ex) {}
                }
            }
        };
        gameThread.start();  // Invoke GaemThread.run()
    }

    /**
     * One game time-step.
     * Update the game objects, with proper collision detection and response.
     */
    public void gameUpdate() {
        ball.moveOneStepWithCollisionDetection(box);
    }

    //NAMED INNER CLASS (has access to outer class's members
    /** The custom drawing panel for the bouncing ball (inner class). */
    class DrawCanvas extends JPanel {

        public DrawCanvas(){
            //setPreferredSize(new Dimension(canvasWidth, canvasHeight));   // this is poor coding style apparently + its a hint only and layout managers, for instance, can resize
                                                                            //preferred method is to override getPreferredSize method (see below) which get called whenever - it's still only a hint though
                                                                            //getPreferredSize is a Swing callback method that's called whenever Swing need to know the preferred size (it's an override of a JComponent method)
        }

        /** Custom drawing codes */
        @Override
        public void paintComponent(Graphics g) {
            /*
            If a Swing component's opaque property is set to true [default for JPanels in most L&Fs), then it is agreeing to paint all of the bits contained within its bounds
            (this includes clearing it's own background within paintComponent()), otherwise screen garbage may result.
            Extensions of Swing components which have UI delegates (including JPanel), should typically invoke super.paintComponent() within their own paintComponent() implementation.
            Since the UI delegate will take responsibility for clearing the background on opaque components, this will take care of the screen garbage problem.
             */

            super.paintComponent(g);    // Paint background - this seems unnecessary as this JPanel is opaque (and it does completely paint its own background) so ancestor components do not need to first redraw themselves using a super.paintComponent chain
                                        // would be necessary if this opaque (specified by default) JPanel did not completely draw all of its screen area.
            // Draw the box and the ball
            box.draw(g);
            ball.draw(g);
            // Display ball's information
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier New", Font.PLAIN, 12));
            g.drawString("bouncingBalls.Ball " + ball.toString(), 20, 30);
        }

//        /** Called back to get the preferred size of the component. */
//        //
//        @Override
//        public Dimension getPreferredSize() {
//            return (new Dimension(canvasWidth, canvasHeight));
//        }
    }
}