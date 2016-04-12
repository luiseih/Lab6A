package Drawing;

import javax.swing.*;
import java.awt.*;

public class Drawing003 extends JFrame {
    public static final int WIDTH = 750;
    public static final int HEIGHT = 750;

    public Drawing003(){
        super("Shapes with random dimensions and transparency");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Drawing003Panel panel = new Drawing003Panel();

        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setContentPane(panel);
        pack();
    }

    public static void main(String[] args) {

        Drawing003 drawing = new Drawing003();

        drawing.setVisible(true);

    }

    private class Drawing003Panel extends JPanel {
        int currentX, currentY; //in java coordinate
        int newX, newY;
        double currentD; //in radians (input in degrees and converted)

        final int ALPHA = 128;
        Color[] colors ={   new Color(255, 0, 0, ALPHA), new Color(255, 127, 0, ALPHA), new Color(255, 255, 0, ALPHA), new Color(0, 255, 0, ALPHA),
                            new Color(0, 0, 255, ALPHA), new Color(75, 0, 130, ALPHA), new Color(143, 0, 255, ALPHA)};


        public Drawing003Panel(){
            super();
            currentX = 0;
            currentY = 0;
            currentD = 0;
        }

        private boolean move(int dist, Graphics2D g2){

            newX = currentX + (int) Math.round(dist * Math.cos(currentD));
            newY = currentY + (int) Math.round(dist * Math.sin(currentD));

            g2.drawLine(currentX, currentY, newX, newY);

            currentX = newX;
            currentY = newY;

            return true;
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

            int x, y, w, h, clientWidth, clientHeight;
            double scale;
            float rC, gC, bC;
            Color c = new Color(0F, 0F, 0F);
            boolean clipped, tooSmall;

            Graphics2D g2 = (Graphics2D) g;

            clientWidth = getSize().width;
            clientHeight = getSize().height;


            for (int i = 1; i <= 200; i++){
                x = (int)(Math.floor(Math.random() * clientWidth));
                y = (int)(Math.floor(Math.random() * clientHeight));

                scale = 0.2;
                w = (int)(scale * Math.floor(Math.random() * getSize().width));
                h = (int)(scale * Math.floor(Math.random() * getSize().height));

                g2.setColor(colors[i % 7]);

                clipped = (x > clientWidth - w) || (y > clientHeight - h);
                tooSmall = (w < 0.2 * scale * clientWidth) || (h < 0.2 * scale * clientHeight);

                if (clipped || tooSmall) //prevent clipping
                    i--; // dangerous hack
                else
                    g2.fillOval(x, y, w, h);

            }



            //strokeWidth = (int)(Math.floor(Math.random() * 10));
            //g2.setStroke(new BasicStroke(strokeWidth));

            //g2.setColor(new Color(rC, gC, bC));

            //g2.drawLine(sx, sy, ex, ey);
        }

    }

}

