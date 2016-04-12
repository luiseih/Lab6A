package Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Drawing003_2 extends JFrame implements ActionListener {
    int currentX = 0, currentY = 0; //in java coordinate
    int newX, newY;
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

    public Drawing003_2(){
        super("Shapes with random dimensions and transparency");

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
        Drawing003_2 drawing = new Drawing003_2();
        drawing.setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
        Graphics2D g = (Graphics2D) drawingBox.getGraphics();

        int x, y, w, h, clientWidth, clientHeight;
        double scale;
        float rC, gC, bC;
        Color c = new Color(0F, 0F, 0F);
        boolean clipped, tooSmall;

        clientWidth = drawingBox.getWidth();
        clientHeight = drawingBox.getHeight();


        for (int i = 1; i <= 200; i++){
            x = (int)(Math.floor(Math.random() * clientWidth));
            y = (int)(Math.floor(Math.random() * clientHeight));

            scale = 0.2;
            w = (int)(scale * Math.floor(Math.random() * getSize().width));
            h = (int)(scale * Math.floor(Math.random() * getSize().height));

            g.setColor(colors[i % 7]);

            clipped = (x > clientWidth - w) || (y > clientHeight - h);
            tooSmall = (w < 0.2 * scale * clientWidth) || (h < 0.2 * scale * clientHeight);

            if (clipped || tooSmall) //prevent clipping
                i--; // dangerous hack
            else
                g.fillOval(x, y, w, h);

            doNothing(25);

        }

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

