package Drawing;

import javax.swing.*;
import java.awt.*;

public class Drawing004 extends JFrame {
    public static final int WIDTH = 750;
    public static final int HEIGHT = 750;

    public Drawing004(){
        super("Drunkard's Walk");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Drawing004Panel panel = new Drawing004Panel();

        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setBackground(Color.black);

        setContentPane(panel);
        pack();
    }

    public static void main(String[] args) {

        Drawing004 drawing = new Drawing004();

        drawing.setVisible(true);

    }


    private class Drawing004Panel extends JPanel {
        int currentX, currentY; //in java coordinate
        double currentD; //in radians (input in degrees and converted)

        final int ALPHA = 128;
        Color[] colors ={   new Color(255, 0, 0, ALPHA), new Color(255, 127, 0, ALPHA), new Color(255, 255, 0, ALPHA), new Color(0, 255, 0, ALPHA),
                new Color(0, 0, 255, ALPHA), new Color(75, 0, 130, ALPHA), new Color(143, 0, 255, ALPHA)};


        public Drawing004Panel(){
            super();
            currentX = 0;
            currentY = 0;
            currentD = 0;
        }

        private boolean move(int dist, Graphics2D g2){
            int newX, newY;
            boolean xClip, yClip;

            newX = currentX + (int) Math.round(dist * Math.cos(currentD));
            newY = currentY + (int) Math.round(dist * -Math.sin(currentD));

            xClip = newX < 0 || newX > getSize().width;
            yClip = newY < 0 || newY > getSize().height;

            if (!xClip && !yClip){ // stop clipping
                g2.drawLine(currentX, currentY, newX, newY);

                currentX = newX;
                currentY = newY;

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


        public void paintComponent(Graphics g){ // g is actually a Graphics2D object (Graphics2D is a subclass of Graphics)
            Graphics2D g2 = (Graphics2D) g;

            currentX = 375; currentY = 375;
            currentD = 0.0;


            g2.setStroke(new BasicStroke(3));

            currentX = 375; currentY = 375;
            doADrunkWalk(60, 500, new Color(0, 0, 255, 128), g2);

            currentX = 375; currentY = 375;
            doADrunkWalk(30, 300, new Color(0, 255, 0, 128), g2);

            currentX = 375; currentY = 375;
            doADrunkWalk(15, 700, new Color(255, 255, 0, 64), g2);

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
    }
}

