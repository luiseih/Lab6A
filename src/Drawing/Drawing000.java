package Drawing;

import javax.swing.*;
import java.awt.*;

public class Drawing000 extends JFrame {
	public static final int WIDTH = 750;
	public static final int HEIGHT = 750;

	public Drawing000(){
		super("Getting the client area and only the client area");
		
		setSize(WIDTH, HEIGHT); //includes borders etc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//getContentPane().setBackground(Color.WHITE);
		
		Drawing000Panel panel = new Drawing000Panel();
		//panel.setSize(WIDTH, HEIGHT);
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setContentPane(panel);
		pack();
	}
	
	
	public static void main(String[] args) {
		
		Drawing000 drawing = new Drawing000();
			
		drawing.setVisible(true);
	}


	private class Drawing000Panel extends JPanel {
		public Drawing000Panel(){
			super();
		}
		
		public void paintComponent(Graphics g){ // g is actually a Graphics2D object (Graphics2D is a subclass of Graphics)
			
			Graphics2D g2 = (Graphics2D) g;
			
			//System.out.println(this.getSize().width);
			//System.out.println(this.getSize().height);
			
			g2.drawLine(0, 0, this.getSize().width, this.getSize().height);
		}
	}

}
