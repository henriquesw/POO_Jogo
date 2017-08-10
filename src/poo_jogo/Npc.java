package poo_jogo;

import java.awt.Image;
import java.util.ArrayList;

public class Npc {
	
	private int x = 1088, y = 448;
	private Image image, currentImage;
	private Image currentSpeak;
	private boolean end = false;
	
	private ArrayList<Image> imagesSpeak = new ArrayList<>();
	
	public Npc(Image image) {
		this.image = image;
	}
	
	public void update(int xPlayer) {
		if((Level.getNivel()-1) == 6) {
			setCurrentImage();
			if(xPlayer + 128 >= x && end == false) {
				StartingClass.setPause(true);
				startSpeak();
				end = true;
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
		currentSpeak = imagesSpeak.get(0);
		Level.setCurrentEnd();
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
	
	public void addImage(Image imageSpeak) {
		imagesSpeak.add(imageSpeak);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
