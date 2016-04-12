package Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Drawing004_2 extends JFrame implements ActionListener {
    int currentX = 0, currentY = 0; //in java coordinate

    double currentD = 0.0; //in radians (input in degrees and converted)

    final int ALPHA = 128;
    Color[] colors ={   new Color(255, 0, 0, ALPHA),
            new Color(255, 127, 0, ALPHA),
            new Color(255, 255, 0, ALPHA),
            new Color(0, 255, 0, ALPHA),
            new Color(0, 0, 255, ALPHA),
            new Color(75, 0, 130, ALPHA),
            new Color(143, 0, 255, ALPHA)
    };


    private JPanel contentBox, drawingBox;

    public static final int WIDTH = 750;
    public static final int HEIGHT = 750;

    public Drawing004_2(){
        super("Drunkard's Walk");

        //setSize(WIDTH, HEIGHT); //includes borders etc
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setLayout(new BorderLayout());

        contentBox = new JPanel();
        contentBox.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setContentPane(contentBox);
        contentBox.setLayout(new BorderLayout());

        drawingBox = new JPanel();
        drawingBox.setPreferredSize(new Dimension((int)(0.9 * WIDTH), (int)(0.9 * HEIGHT)));
        drawingBox.setBackground(Color.black);
        contentBox.add(drawingBox, BorderLayout.CENTER);

        JButton button = new JButton("Draw");
        button.addActionListener(this);
        contentBox.add(button, BorderLayout.SOUTH);

        pack();
    }


    public static void main(String[] args) {
        Drawing004_2 drawing = new Drawing004_2();
        drawing.setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
        Graphics2D g = (Graphics2D) drawingBox.getGraphics();

        int clientWidth, clientHeight;
        int small, bigger, biggest;

        clientWidth = drawingBox.getWidth();
        clientHeight = drawingBox.getHeight();

        small = (int)(clientWidth / 60.0);
        bigger = (int)(clientWidth / 30.0);
        biggest = (int)(clientWidth / 15.0);


        currentX = (int)(clientWidth / 2.0); currentY = (int)(clientHeight / 2.0);
        currentD = 0.0;


        g.setStroke(new BasicStroke(3));

        currentX = (int)(clientWidth / 2.0); currentY = (int)(clientHeight / 2.0);
        doADrunkWalk(biggest, 300, new Color(0, 0, 255, 128), g);

        currentX = (int)(clientWidth / 2.0); currentY = (int)(clientHeight / 2.0);
        doADrunkWalk(bigger, 500, new Color(0, 255, 0, 128), g);

        currentX = (int)(clientWidth / 2.0); currentY = (int)(clientHeight / 2.0);
        doADrunkWalk(small, 1000, new Color(255, 255, 0, 64), g);

        }

    private void doADrunkWalk(int straightDist, int repetitions, Color colour, Graphics2D g2){
        int directionNumber;
        boolean moveWouldClip;

        g2.setColor(colour);

        for (int i = 1; i <= repetitions; i++){
            moveWouldClip = !move(straightDist, g2);

            if (moveWouldClip)
                i--; //dangerous hack

            directionNumber = 1 + (int)(Math.floor(Math.random() * 4.0));
            changeDirection(directionNumber * 90);
        }
    }

    private boolean move(int dist, Graphics2D g2){
        int newX, newY;
        boolean xClip, yClip;

        newX = currentX + (int) Math.round(dist * Math.cos(currentD));
        newY = currentY + (int) Math.round(dist * -Math.sin(currentD));

        xClip = newX < 0 || newX > drawingBox.getWidth();
        yClip = newY < 0 || newY > drawingBox.getHeight();


        if (!xClip && !yClip){ // stop clipping
            g2.drawLine(currentX, currentY, newX, newY);
            System.out.println(newX);

            currentX = newX;
            currentY = newY;

            doNothing(10);

            return true;
        }
        else
            return false;
    }

    private boolean setDirection(int degrees){
        currentD = Math.toRadians(360 - (degrees % 360));
        return true;
    }

    private boolean changeDirection(int changeDegrees){
        currentD += Math.toRadians(360 - (changeDegrees % 360));
        return true;
    }

    public void doNothing(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException e){
            System.out.println("Unexpected interrupt");
            System.exit(0);
        }
    }

}

