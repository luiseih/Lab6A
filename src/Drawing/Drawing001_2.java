package Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Drawing001_2 extends JFrame implements ActionListener {
    private JPanel contentBox, drawingBox;

    public static final int WIDTH = 750;
    public static final int HEIGHT = 750;

    public Drawing001_2(){
        super("Random Lines");

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
        Drawing001_2 drawing = new Drawing001_2();
        drawing.setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
        Graphics2D g = (Graphics2D) drawingBox.getGraphics();
        //g.drawLine(0, 0, drawingBox.getSize().width, drawingBox.getSize().height);


        int sx, sy, ex, ey;
        float rC, gC, bC;
        Color c = new Color(0F, 0F, 0F);
        int strokeWidth;
        int clientWidth = drawingBox.getWidth(), clientHeight = drawingBox.getHeight();


        for (int i = 1; i <= 100; i++){
            sx = (int)(Math.floor(Math.random() * clientWidth));
            sy = (int)(Math.floor(Math.random() * clientHeight));
            ex = (int)(Math.floor(Math.random() * clientWidth));
            ey = (int)(Math.floor(Math.random() * clientHeight)); //random orientation
            //ey =  sy; //horizontal orientation

            rC = (float) Math.random();
            gC = (float) Math.random();
            bC = (float) Math.random();

            strokeWidth = (int)(Math.floor(Math.random() * 7));
            g.setStroke(new BasicStroke(strokeWidth));

            g.setColor(new Color(rC, gC, bC));

            g.drawLine(sx, sy, ex, ey);

            doNothing(50);
        }
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

