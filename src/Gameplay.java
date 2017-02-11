import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private int lengthofsnake = 3;
	
	//manage the speed of the snake
	private Timer timer;
	private int delay = 100;
	
	private ImageIcon snakeimage;
	
	private int [] appleXPos={ 25, 50, 75, 100, 125, 150, 175, 200, 225, 250
		, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575
		, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850
	};
	//start at 75 because the first 75 pixels are the title of the game
	private int [] appleYPos={ 75, 100, 125, 150, 175, 200, 225, 250
			, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575
			, 600, 625
	};
	
	private ImageIcon appleImage;
	
	private Random random = new Random();
	
	private int xPos = random.nextInt(34); //total number of X positions for the apple
	private int yPos = random.nextInt(23); //total number of Y positions for the apple
	
	private int score = 0;
	
	private int moves = 0;
	
	private ImageIcon titleImage;
	
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		//set default position of the snake		
		if (moves == 0) {
			snakexlength[2] = 50;
			snakexlength[1] = 75;
			snakexlength[0] = 100;
			
			snakeylength[2] = 100;
			snakeylength[1] = 100;
			snakeylength[0] = 100;
		}
		// draw title image border
		g.setColor(Color.DARK_GRAY);
		g.fillRect(10, 10, 905, 700);		
		
		// draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		// draw the title image
//		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage = new ImageIcon(getClass().getResource("/Resources/snaketitle.jpg"));
		titleImage.paintIcon(this, g, 25, 11);
		
		// draw the border for playing area
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 852, 577);
		
		// draw the background for the playing area
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Scores: " + score, 780, 30);
		
		//draw the length of the snake
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length: " + lengthofsnake, 780, 50);
		
		
		
//		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth = new ImageIcon(getClass().getResource("/Resources/rightmouth.png"));
		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int a = 0; a< lengthofsnake; a++){
			if(a == 0 && right) {
//				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth = new ImageIcon(getClass().getResource("/Resources/rightmouth.png"));
				rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a == 0 && left) {
//				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth = new ImageIcon(getClass().getResource("/Resources/leftmouth.png"));
				leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a == 0 && down) {
//				downmouth = new ImageIcon("downmouth.png");
				downmouth = new ImageIcon(getClass().getResource("/Resources/downmouth.png"));
				downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a == 0 && up) {
//				upmouth = new ImageIcon("upmouth.png");
				upmouth = new ImageIcon(getClass().getResource("/Resources/upmouth.png"));
				upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			//if a (first index) doesnt equal zero then we are drawing the body, not the head
			if(a != 0){
//				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage = new ImageIcon(getClass().getResource("/Resources/snakeimage.png"));
				snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			
		}
		
//		appleImage = new ImageIcon("enemy.png");
		appleImage = new ImageIcon(getClass().getResource("/Resources/enemy.png"));
		appleImage.paintIcon(this, g, appleXPos[xPos], appleYPos[yPos]);
		
		//detect if head of snake is colliding with apple
		if(appleXPos[xPos] == snakexlength[0] && appleYPos[yPos] == snakeylength[0]){
			lengthofsnake++;
			score++;
			//create new apple
			xPos = random.nextInt(34); 
			yPos = random.nextInt(23);
		}
		
		//check for head collision
		for(int b = 1; b < lengthofsnake; b++){
			//with the snake itself
			if(snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]){
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press Space to RESTART", 320, 340);				
			}
			//with the border
			if(snakexlength[0] > 850 || snakexlength[0] < 25 || snakeylength[0] > 625 || snakeylength[0] < 75){
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press Space to RESTART", 320, 340);	
			}
		}
			
		upmouth = new ImageIcon(getClass().getResource("/Resources/upmouth.png"));
//		upmouth = new ImageIcon("upmouth.png");
		downmouth = new ImageIcon(getClass().getResource("/Resources/downmouth.png"));
//		downmouth = new ImageIcon("downmouth.png");
		leftmouth = new ImageIcon(getClass().getResource("/Resources/leftmouth.png"));
//		leftmouth = new ImageIcon("leftmouth.png");
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right){
			for(int r = lengthofsnake-1; r>=0; r--) {
				snakeylength[r+1] = snakeylength[r];  // if going right then y stays the same
			}
			for(int r = lengthofsnake; r>=0; r--){
				if (r==0){
					snakexlength[r] = snakexlength[r] + 25; // the head should move forward 25 pixels
				}
				else {
					snakexlength[r] = snakexlength[r-1]; // the body will take the place of the one behind it
				}
				//check if snake hits border then loop it
//				if(snakexlength[r] > 850){
//					snakexlength[r] = 25;
//				}
			}
			
			repaint();  //calls that "paint" method above
		}
		if(left){
			for(int r = lengthofsnake-1; r>=0; r--) {
				snakeylength[r+1] = snakeylength[r];  // if going right then y stays the same
			}
			for(int r = lengthofsnake; r>=0; r--){
				if (r==0){
					snakexlength[r] = snakexlength[r] - 25; // the head should move forward 25 pixels
				}
				else {
					snakexlength[r] = snakexlength[r-1]; // the body will take the place of the one behind it
				}
				//check if snake hits border then loop it
//				if(snakexlength[r] < 25){
//					snakexlength[r] = 850;
//				}
			}
			
			repaint();  //calls that "paint" method above			
		}
		if(down){
			for(int r = lengthofsnake-1; r>=0; r--) {
				snakexlength[r+1] = snakexlength[r];  // if going right then y stays the same
			}
			for(int r = lengthofsnake; r>=0; r--){
				if (r==0){
					snakeylength[r] = snakeylength[r] + 25; // the head should move forward 25 pixels
				}
				else {
					snakeylength[r] = snakeylength[r-1]; // the body will take the place of the one behind it
				}
				//check if snake hits border then loop it
//				if(snakeylength[r] > 625){
//					snakeylength[r] = 75;
//				}
			}
			
			repaint();  //calls that "paint" method above				
		}
		if(up){
			for(int r = lengthofsnake-1; r>=0; r--) {
				snakexlength[r+1] = snakexlength[r];  // if going right then y stays the same
			}
			for(int r = lengthofsnake; r>=0; r--){
				if (r==0){
					snakeylength[r] = snakeylength[r] - 25; // the head should move forward 25 pixels
				}
				else {
					snakeylength[r] = snakeylength[r-1]; // the body will take the place of the one behind it
				}
				//check if snake hits border then loop it
//				if(snakeylength[r] < 75){
//					snakeylength[r] = 625;
//				}
			}
			
			repaint();  //calls that "paint" method above				
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//SPACE
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			moves = 0;
			score = 0;
			lengthofsnake = 3;
			repaint();
		}
		//RIGHT
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			moves++;
			this.right = true;
			if (!left){
				right = true;
			} 
			else {
				right = false;
				left = true;
			}
			this.up = false;
			this.down = false;
		}
		//LEFT
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			moves++;
			this.left = true;
			if (!right){
				left = true;
			} 
			else {
				left = false;
				right = true;
			}
			this.up = false;
			this.down = false;
		}	
		//UP
		if(e.getKeyCode() == KeyEvent.VK_UP){
			moves++;
			this.up = true;
			if (!down){
				up = true;
			} 
			else {
				up = false;
				down = true;
			}
			this.left = false;
			this.right = false;
		}
		//DOWN
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			moves++;
			this.down = true;
			if (!up){
				down = true;
			} 
			else {
				down = false;
				up = true;
			}
			this.left = false;
			this.right = false;
		}				
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
