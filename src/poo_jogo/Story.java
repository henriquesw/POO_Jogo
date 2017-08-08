package poo_jogo;

import java.awt.Image;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Story implements Runnable {

	private ArrayList<Image> images = new ArrayList<>();
	private Image currentStory;
	private int niveis[][] = new int[3][2];
	private boolean pause = false;
	
	public void addImage(Image image) {
		images.add(image);
	}
	
	public void setCurrentStory(int i) {
		currentStory = images.get(i);
	}
	
	public Image getCurrentStory() {
		return currentStory;
	}

	@Override
	public void run() {
		if(Level.getStoryBegin() != 0) {
			pause = true;
			for(int i = Level.getStoryBegin(); i <= Level.getStoryEnd(); i++) {
				setCurrentStory(i);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					
				}
			}
			setCurrentStory(0);
			pause = false;
		}
	}
	
	public boolean getPause() {
		return pause;
	}
}
