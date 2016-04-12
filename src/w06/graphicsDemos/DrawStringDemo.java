package w06.graphicsDemos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawStringDemo extends JFrame implements ActionListener {
    public static final int WIDTH = 350;
    public static final int HEIGHT = 200;
    public static final int X_START = 20;
    public static final int Y_START = 100;
    public static final int POINT_SIZE = 24;

    private String theText = "Push the button.";
    private Color penColor = Color.BLACK;
    private Font fontObject = new Font("SansSerif", Font.PLAIN, POINT_SIZE);

    public static void main(String[] args){
        DrawStringDemo gui = new DrawStringDemo( );
        gui.setVisible(true);
    }


    public DrawStringDemo( ){
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("drawString Demonstration");
        getContentPane( ).setBackground(Color.WHITE);
        setLayout(new BorderLayout( ));

        JPanel buttonPanel = new JPanel( );
        buttonPanel.setBackground(Color.ORANGE);
        buttonPanel.setLayout(new FlowLayout( ));

        JButton theButton = new JButton("The Button");
        theButton.setPreferredSize(new Dimension(100, 20)); //works when using FlowLayout but not BorderLayout
        theButton.addActionListener(this);

        buttonPanel.add(theButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }


    public void paint(Graphics g){
        super.paint(g);
        g.setFont(fontObject);
        g.setColor(penColor);
        g.drawString(theText, X_START, Y_START);
    }


    public void actionPerformed(ActionEvent e){
        penColor = Color.RED; //sticks until next penColor set
        fontObject = new Font("Serif", Font.BOLD| Font.ITALIC, POINT_SIZE); //persists until next fontObject set
        theText = "Thank you. I needed that.";

        repaint( ); //redraw entire JFrame, repaint does some admin then calls paint which uses the colour and font and String just set
    }
}
