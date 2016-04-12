package Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Drawing000_2 extends JFrame implements ActionListener {
    private JPanel contentBox, drawingBox;

	public static final int WIDTH = 750;
	public static final int HEIGHT = 750;

	public Drawing000_2(){
		super("Getting the client area and only the client area");
		
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
		Drawing000_2 drawing = new Drawing000_2();
		drawing.setVisible(true);
	}


    public void actionPerformed(ActionEvent e){
        Graphics2D g = (Graphics2D) drawingBox.getGraphics();
        g.drawLine(0, 0, drawingBox.getSize().width, drawingBox.getSize().height);

//        System.out.println(this.getSize().width + ", " + this.getSize().height);
//        System.out.println(contentBox.getSize().width + ", " + contentBox.getSize().height);
    }


}
