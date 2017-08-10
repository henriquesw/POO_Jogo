package poo_jogo;

import java.awt.Image;
import java.util.ArrayList;

public class Player {

	// Constants are Here
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	final int GROUND = 640;
	
	private int x;
	private int y;
	private int heigth = 64;
	private int width = 48;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private boolean ended = false;
	
	private ArrayList<Image> images = new ArrayList<>();
	private Image currentImage;

	private int speedX = 0;
	private int speedY = 1;
	
	public void update(Level level) {
		// Moves Character

		if (speedX < 0) {
			x += speedX;
		}

		if (speedX > 0) {
			x += speedX;
		}

		// Updates Y Position
		y += speedY;

		collision(level.getMatriz());
		
		// Handles Jumping
		if (jumped == true) {
			speedY += 1;
		}

		// Prevents going beyond X coordinate of 0
		if (x + speedX <= 0) {
			x = 0;
		}
		
		if (x + speedX >= 1280) {
			ended = true;
			stopRight();
			setCurrentImage(0);
		}
		
		if(y+speedY >= 768) {
			x = level.getStartX();
			y = level.getStartY();
		}
		
	}
	
	private void collision(int matriz[][]) {
		int i; 
		int j;
		Boolean test;
		
		try {
		//colisão pular para baixo
		if (speedY >= 0) {
			test = true;
			i = (y+heigth)/64;
			j = (x+12)/64;
			
			if (matriz[i][j] != 0) {
				y = ((i-1)*64);
				speedY = 0;
				jumped = false;
				test = false;
			}
			i = (y+heigth)/64;
			j = (x+width-12)/64;
			if (matriz[i][j] != 0) {
				y = ((i-1)*64);
				speedY = 0;
				jumped = false;
				test = false;
			}
			if (test) {
				jumped = true;
			}
			
		}
		// colisão pulo para cima
		if (speedY < 0) {
			i = y/64;
			j = (x+12)/64;
			if (matriz[i][j] != 0) {
				y = ((i+1)*64);
				speedY = 0;
			}
		}
		//andar direita
		if (speedX > 0) {
			i = y/64;
			j = (x+width-8)/64;
			if (matriz[i][j] != 0) {
				x = ((j-1)*64)+24;
				stopRight();
			}
		}
		//andar esquerda
		if (speedX < 0) {
			i = y/64;
			j = (x+8)/64;
			if (matriz[i][j] != 0) {
				x = ((j+1)*64)-8;
				stopLeft();
			}
		}
		} catch (ArrayIndexOutOfBoundsException e) {
			
		}
	}

	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

	}

	public void jump() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			jumped = true;
		}

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isJumped() {
		return jumped;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}
	
	public boolean getEnded() {
		return ended;
	}
	
	public void add(Image image) {
		images.add(image);
	}
	
	public void setCurrentImage(int i) {
		currentImage = images.get(i);
	}
	
	public Image getCurrentImage() {
		return currentImage;
	}
}
