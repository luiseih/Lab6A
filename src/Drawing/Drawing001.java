package Drawing;

import javax.swing.*;
import java.awt.*;

public class Drawing001 extends JFrame {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;

	public Drawing001(){
		super("Random Lines");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Drawing001Panel panel = new Drawing001Panel();
		//panel.setSize(WIDTH, HEIGHT);
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setContentPane(panel);
		pack();

	}
	
	
	public static void main(String[] args) {
		
		Drawing001 drawing = new Drawing001();
			
		drawing.setVisible(true);
		
	}
	
	private class Drawing001Panel extends JPanel {
		public Drawing001Panel(){
			super();
		}
		
		public void paintComponent(Graphics g){ // g is actually a Graphics2D object (Graphics2D is a subclass of Graphics)
			
			int sx, sy, ex, ey;
			float rC, gC, bC;
			Color c = new Color(0F, 0F, 0F);
			int strokeWidth;
			
			Graphics2D g2 = (Graphics2D) g;
			
			for (int i = 1; i <= 100; i++){
				sx = (int)(Math.floor(Math.random() * getSize().width));
				sy = (int)(Math.floor(Math.random() * getSize().height));
				ex = (int)(Math.floor(Math.random() * getSize().width));
                ey = (int)(Math.floor(Math.random() * getSize().height)); //random orientation
				//ey =  sy; //horizontal orientation
				
				rC = (float) Math.random();
				gC = (float) Math.random();
				bC = (float) Math.random();
				
				strokeWidth = (int)(Math.floor(Math.random() * 7));
				g2.setStroke(new BasicStroke(strokeWidth));
				
				g2.setColor(new Color(rC, gC, bC));
				
				g2.drawLine(sx, sy, ex, ey);
			}	

		}
	}

}

