package poo_jogo;

import java.awt.Image;
import java.util.ArrayList;

public class Story {

	private ArrayList<Image> images = new ArrayList<>();
	private Image currentStory;
	
	
	
	public void addImage(Image image) {
		images.add(image);
	}
	
	public void setCurrentStory() {
		currentStory = images.get(0);
	}
	
	public Image getCurrentStory() {
		return currentStory;
	}
	
}
