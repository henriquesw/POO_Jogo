package poo_jogo;


public class Level {
	
	private int bgX, bgY;
	
	public Level(int x, int y){
		bgX = x;
		bgY = y;
	}
	
	public void update() {

	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}	
	
}
