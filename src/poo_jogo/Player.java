package poo_jogo;

public class Player {

	// Constants are Here
	final int JUMPSPEED = -15;
	final int GRAVITY = 1;
	final int MOVESPEED = 5;
	final int GROUND = 640;
	
	private int x = 64;
	private int y = 576;
	private int heigth = 64;
	private int width = 48;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;

	private int speedX = 0;
	private int speedY = 1;
	
	public void update(int[][] matriz) {
		// Moves Character

		if (speedX < 0) {
			x += speedX;
		}

		if (speedX > 0) {
			x += speedX;
		}

		// Updates Y Position
		y += speedY;
		if (y + heigth + speedY >= GROUND) {
			y = GROUND - heigth;
		}

		int i; 
		int j;
		
		if (speedY > 0) {
			i = (y+heigth)/64;
			j = x/64;
			if (matriz[i][j] != 0) {
				y = ((i-1)*64);
				jumped = false;
			}
		}
		
		if (speedY < 0) {
			i = y/64;
			j = x/64;
			if (matriz[i][j] != 0) {
				y = ((i+1)*64);
				speedY = 0;
			}
		}
		
		if (speedX > 0) {
			i = y/64;
			j = (x+width)/64;
			if (matriz[i][j] != 0) {
				x = ((j-1)*64)+16;
				stopRight();
			}
		}
		
		if (speedX < 0) {
			i = y/64;
			j = x/64;
			if (matriz[i][j] != 0) {
				x = ((j+1)*64);
				stopLeft();
			}
		}
		
		
		
		// Handles Jumping
		if (jumped == true) {
			speedY += 1;

			if (y + heigth + speedY >= GROUND) {
				y = GROUND - heigth;
				speedY = 0;
				jumped = false;
			}

		}

		// Prevents going beyond X coordinate of 0
		if (x + speedX <= 0) {
			x = 0;
		}
		
		if (x + speedX >= 1230) {
			x = 1230;
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

	public void setCenterX(int centerX) {
		this.x = centerX;
	}

	public void setCenterY(int centerY) {
		this.y = centerY;
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

}
