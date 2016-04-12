package w06.graphicsDemos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionFace extends JFrame {
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 400;

    public static final int FACE_DIAMETER = 200;
    public static final int X_FACE = 100;
    public static final int Y_FACE = 100;

    public static final int EYE_WIDTH = 20;
    public static final int EYE_HEIGHT = 10;
    public static final int X_RIGHT_EYE = X_FACE + 55;
    public static final int Y_RIGHT_EYE = Y_FACE + 60;
    public static final int X_LEFT_EYE = X_FACE + 130;
    public static final int Y_LEFT_EYE = Y_FACE + 60;

    public static final int MOUTH_WIDTH = 100;
    public static final int MOUTH_HEIGHT = 50;
    public static final int X_MOUTH = X_FACE + 50;
    public static final int Y_MOUTH = Y_FACE + 100;
    public static final int MOUTH_START_ANGLE = 180;
    public static final int MOUTH_ARC_SWEEP = 180;

    private boolean wink; //auto-initialised as false but see constructor for explicit initialisation

    //INNER CLASS (listener)
    private class WinkAction implements ActionListener {
        public void actionPerformed(ActionEvent e){
            wink = true; 
            repaint( ); //complete redraw of face
                        //always call repaint not paint, never override repaint (cf paint)
        }
    } // End of WinkAction inner class


    public static void main(String[] args){
        ActionFace drawing = new ActionFace( );
        drawing.setVisible(true);
    }

    public ActionFace( ){
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Hello There!"); 
        setLayout(new BorderLayout( ));
        getContentPane( ).setBackground(Color.white);

        JButton winkButton = new JButton("Click for a Wink.");
        winkButton.addActionListener(new WinkAction( ));
        add(winkButton, BorderLayout.SOUTH);
        wink = false; //initially eye is oval i.e. open
    }

    public void paint(Graphics g){ //never call paint, always override it (cf repaint)
        super.paint(g);
        g.drawOval(X_FACE, Y_FACE, FACE_DIAMETER, FACE_DIAMETER);

        //Draw Right Eye:
        g.fillOval(X_RIGHT_EYE, Y_RIGHT_EYE, EYE_WIDTH, EYE_HEIGHT);
        //Draw Left Eye:
        if (wink)
            g.drawLine(X_LEFT_EYE, Y_LEFT_EYE, X_LEFT_EYE + EYE_WIDTH, Y_LEFT_EYE);
        else
            g.fillOval(X_LEFT_EYE, Y_LEFT_EYE, EYE_WIDTH, EYE_HEIGHT);

        //Draw Mouth:
        g.drawArc(X_MOUTH, Y_MOUTH, MOUTH_WIDTH, MOUTH_HEIGHT, MOUTH_START_ANGLE, MOUTH_ARC_SWEEP);
    }

}
