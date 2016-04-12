package Drawing;

import javax.swing.*;
import java.awt.*;

public class Drawing002 extends JFrame {
	public static final int WIDTH = 750;
	public static final int HEIGHT = 750;

	public Drawing002(){
		super("Move and Change Direction");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Drawing002Panel panel = new Drawing002Panel();

		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		setContentPane(panel);
		pack();
	}

	public static void main(String[] args) {
		
		Drawing002 drawing = new Drawing002();
			
		drawing.setVisible(true);
		
	}
	
	private class Drawing002Panel extends JPanel {
        int currentX, currentY; //in java coordinate
		int newX, newY;
		double currentD; //in radians (input in degrees and converted)
        Color[] colors ={   new Color(255, 0, 0), new Color(255, 127, 0), new Color(255, 255, 0), new Color(0, 255, 0),
                            new Color(0, 0, 255), new Color(75, 0, 130), new Color(143, 0, 255)};
		
		
		public Drawing002Panel(){
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
			
			int sx, sy, ex, ey;
			float rC, gC, bC;
			Color c = new Color(0F, 0F, 0F);
			int strokeWidth;
			
			Graphics2D g2 = (Graphics2D) g;
			
			setDirection(0);
			currentX = 375; currentY = 375;

            g2.setStroke(new BasicStroke(1));


//            for (int i = 1; i <= 200; i++){
//                //g2.setColor(colors[i % 7]);
//                g2.setColor(new Color(i, 255, 255));
//                move(i * 2, g2);
//                changeDirection(91);
//            }

            int deltaTurn = 1, deltaMove = 1;
            for (int i = 1; i <= 180; i++){
                move(deltaMove, g2);
                changeDirection(deltaTurn);

                deltaTurn = deltaTurn + 1;
                deltaMove = deltaMove + 1;

                g2.setColor(colors[i % 7]);
            }

            //strokeWidth = (int)(Math.floor(Math.random() * 10));
				//g2.setStroke(new BasicStroke(strokeWidth));
				
				//g2.setColor(new Color(rC, gC, bC));
				
				//g2.drawLine(sx, sy, ex, ey);
		}
	}

}

