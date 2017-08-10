package poo_jogo;

import java.awt.Image;
import java.util.ArrayList;

public class Npc {
	
	private int x, y;
	private Image image, currentImage;
	private Image currentSpeak;
	
	private ArrayList<Image> imagesSpeak = new ArrayList<>();
	
	public Npc(Image image) {
		this.image = image;
	}
	
	public void update(int xPlayer) {
		if(Level.getNivel()-1 == 4) {
			setCurrentImage();
			if(xPlayer + 128 == x) {
				StartingClass.setPause(true);
				startSpeak();
			}
		}
	}
	
	private void startSpeak() {
		for(int i = 1; i <= 1; i++) {
			currentSpeak = imagesSpeak.get(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
	}

	public Image getCurrentSpeak() {
		return currentSpeak;
	}
	
	public Image getCurrentImage() {
		return currentImage;
	}
	
	public void setCurrentImage() {
		currentImage = image;
	}
	
}
