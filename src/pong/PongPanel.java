package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PongPanel extends JPanel implements Runnable {
    private final int MAX_SCORE = 5;
    private final int BALL_SIZE = 8;

    //ball and player positions + initialisation of these positions
	private int ballX = 10, ballY = 100;
    private int p1X = 10, p1Y = 100, p2X = 230, p2Y = 100;
    
	Thread gameThread;
    
	final int R_INC = 5, LEFT_INC = -5, UP_INC = 5, DOWN_INC = -5;

	int courtWidth, courtHeight;

	// Scores
	int scorePlayer1 = 0, scorePlayer2 = 0;
	boolean p1WantsToGoUp, p1WantsToGoDown, p2WantsToGoUp, p2WantsToGoDown;

	boolean gameOn;

    //constructor
	public PongPanel() {
		gameOn = true;
		gameThread = new Thread(this); //execute this class's run method on this newly created thread'
		gameThread.start();
	}

	// Draw ball and ships
	public void paintComponent(Graphics gc) {
		setOpaque(false);
		super.paintComponent(gc);

		// Draw ball
		gc.setColor(Color.blue);
		gc.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

		// Draw bats
		gc.fillRect(p1X, p1Y, 10, 25);
		gc.fillRect(p2X, p2Y, 10, 25);

		// Draw scores
		gc.drawString("Player 1: " + scorePlayer1, 25, 10);
		gc.drawString("Player 2: " + scorePlayer2, 150, 10);

		if (!gameOn)
			gc.drawString("Game Over", 100, 125);
	}

	// Positions on X and Y for the ball
	public void drawBall(int nx, int ny) {
		ballX = nx;
		ballY = ny;

		courtWidth = getWidth();
		courtHeight = getHeight();

		repaint();
	}

	// responding to player wants to START moving bat, no move just sets flag for next game loop animation
	public void keyPressed(KeyEvent evt) {
		switch (evt.getKeyCode()) {
            case KeyEvent.VK_W:
                p1WantsToGoUp = true;
                break;
            case KeyEvent.VK_S:
                p1WantsToGoDown = true;
                break;
    
            case KeyEvent.VK_UP:
                p2WantsToGoUp = true;
                break;
            case KeyEvent.VK_DOWN:
                p2WantsToGoDown = true;
                break;
		}
	}

    // responding to player wants to STOP moving bat, no move just sets flag for next game loop animation
	public void keyReleased(KeyEvent evt) {
		switch (evt.getKeyCode()) {
            case KeyEvent.VK_W:
                p1WantsToGoUp = false;
                break;
            case KeyEvent.VK_S:
                p1WantsToGoDown = false;
                break;
    
            case KeyEvent.VK_UP:
                p2WantsToGoUp = false;
                break;
            case KeyEvent.VK_DOWN:
                p2WantsToGoDown = false;
                break;
		}
	}

	// Move player 1
	public void moverPlayer1() {
		if (p1WantsToGoUp && p1Y >= 0)
			p1Y += DOWN_INC;
		if (p1WantsToGoDown && p1Y <= (this.getHeight() - 25))
			p1Y += UP_INC;

        repaint();
	}

	// Move player 2
	public void moverPlayer2() {
		if (p2WantsToGoUp && p2Y >= 0)
			p2Y += DOWN_INC;
		if (p2WantsToGoDown && p2Y <= (this.getHeight() - 25))
			p2Y += UP_INC;

        repaint();
	}


	public void run() {
		boolean movingRight = false;
		boolean movingDown = false;

		while (true) {

			if (gameOn) {

				if (movingRight) {
					ballX += R_INC;
					if (ballX >= (courtWidth - BALL_SIZE))
						movingRight = false;
				} else {
					ballX += LEFT_INC;
					if (ballX <= 0)
						movingRight = true;
				}

				// The ball moves from up to down
				if (movingDown) {
					ballY += UP_INC;
					if (ballY >= (courtHeight - BALL_SIZE))
						movingDown = false;

				} else {
					ballY += DOWN_INC;
					if (ballY <= 0)
						movingDown = true;
				}

				drawBall(ballX, ballY);

				// Delay to control animation frame rate
				try {
					Thread.sleep(50);
				}
                catch (InterruptedException ex) {

				}

				// Move player 1
				moverPlayer1();

				// Move player 2
				moverPlayer2();

				//player 1 scores
				if (ballX >= (getWidth() - BALL_SIZE))
					scorePlayer1++;

				//player 2 scores
				if (ballX == 0)
					scorePlayer2++;

                //check if game over
				if (scorePlayer1 == MAX_SCORE || scorePlayer2 == MAX_SCORE) {
					gameOn = false;

				}

				// The ball stroke with the player 1
				if (ballX <= p1X + 10 && ballY >= p1Y && ballY <= (p1Y + 25))
					movingRight = true;

				// The ball stroke with the player 2
				if (ballX >= (p2X - 5) && ballY >= p2Y && ballY <= (p2Y + 25))
					movingRight = false;
			}
		}
	}

}
