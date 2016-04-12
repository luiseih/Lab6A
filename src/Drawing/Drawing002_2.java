package Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Drawing002_2 extends JFrame implements ActionListener {
    int currentX = 0, currentY = 0; //in java coordinate
    int newX, newY;
    double currentD = 0.0; //in radians (input in degrees and converted)
    Color[] colors ={   new Color(255, 0, 0), new Color(255, 127, 0),
            new Color(255, 255, 0), new Color(0, 255, 0),
            new Color(0, 0, 255), new Color(75, 0, 130), new Color(143, 0, 255)
    };


    private JPanel contentBox, drawingBox;

    public static final int WIDTH = 750;
    public static final int HEIGHT = 750;

    public Drawing002_2(){
        super("Move and Change Direction");

        //setSize(WIDTH, HEIGHT); //includes borders etc
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setLayout(new BorderLayout());

        contentBox = new JPanel();
        contentBox.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setContentPane(contentBox);
        contentBox.setLayout(new BorderLayout());

        drawingBox = new JPanel();
        drawingBox.setPreferredSize(new Dimension((int)(0.9 * WIDTH), (int)(0.9 * HEIGHT)));
        contentBox.add(drawingBox, BorderLayout.CENTER);

        JButton button = new JButton("Draw");
        button.addActionListener(this);
        contentBox.add(button, BorderLayout.SOUTH);

        pack();
    }


    public static void main(String[] args) {
        Drawing002_2 drawing = new Drawing002_2();
        drawing.setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
        Graphics2D g = (Graphics2D) drawingBox.getGraphics();

        int sx, sy, ex, ey;
        float rC, gC, bC;
        Color c = new Color(0F, 0F, 0F);
        int strokeWidth;

        g.setStroke(new BasicStroke(1));


//            for (int i = 1; i <= 200; i++){
//                //g2.setColor(colors[i % 7]);
//                g2.setColor(new Color(i, 255, 255));
//                move(i * 2, g2);
//                changeDirection(91);
//            }

        int thisTurn = 1, thisMove = 1, deltaMove = (int)(drawingBox.getHeight() / 180); //because 180 segments are drawn

        setDirection(0);
        currentX = (int)(drawingBox.getWidth() / 2.0); currentY = (int)(drawingBox.getHeight() / 2.0) + 45 * deltaMove; //so need to start half way down window then roughly hald the height of the finished graphic

        for (int i = 1; i <= 180; i++){
            move(thisMove, g);
            changeDirection(thisTurn);

            thisTurn = thisTurn + 1;
            thisMove = thisMove + deltaMove;

            g.setColor(colors[i % 7]);

            doNothing(50);
        }

        //strokeWidth = (int)(Math.floor(Math.random() * 10));
        //g2.setStroke(new BasicStroke(strokeWidth));

        //g2.setColor(new Color(rC, gC, bC));

        //g2.drawLine(sx, sy, ex, ey);
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

