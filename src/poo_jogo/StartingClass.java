package poo_jogo;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {

	private Player player;
	private Image image, currentSprite, character, characterDown, characterJumped, characterRight, characterLeft, background;
	private Image storyImg;
	private Graphics second;
	private URL base;
	private Level level;
	private Story story;
	private Npc npc;

	private static boolean pause = false;
	
	@Override
	public void init() {

		setSize(1280, 768);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Lost in the world");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void start() {
		level = new Level();
		player = new Player();
		story = new Story();
		
		Image npcImg = getImage(base, "images/player/Player.png");
		
		npc = new Npc(npcImg);
		
		loadImages();
		loadTiles();
		loadStory();
		loadSpeak();
		
		Image end = getImage(base, "images/end/End.png");
		level.setEnd(end);
		
		story.setCurrentStory(0);
		
		level.readFile();
		level.setBackground(loadBackground(Level.getNivel()-2));
		player.setX(level.getStartX());
		player.setY(level.getStartY());

		Thread thread = new Thread(this);
		thread.start();
		Thread threadStory = new Thread(story);
		threadStory.start();
		}
	
	private void loadImages() {
		character = getImage(base, "images/player/Player.png");
		characterRight = getImage(base, "images/player/Player_Direita.gif");
		characterLeft = getImage(base, "images/player/Player_Esquerda.gif");
		characterDown = getImage(base, "images/player/Player_Abaixando.png");
		characterJumped = getImage(base, "images/player/Player_Pulando.png");
		player.add(character);
		player.add(characterRight);
		player.add(characterLeft);
		player.add(characterJumped);
		player.add(characterDown);
		player.setCurrentImage(0);
	}
	
	private void loadTiles() {
		for(int i = 0; i <= 13; i++) {
			Image image = getImage(base, "images/tiles/tile_"+i+".png");
			Tile tile = new Tile(image);
			level.addTile(tile);
		}
	}
	
	private void loadStory() {
		for(int i = 0; i <= 8; i++)	{
			storyImg = getImage(base, "images/story/Story_"+i+".png");
			story.addImage(storyImg);
		}
	}
	
	private void loadSpeak() {
		for(int i = 0; i <= 1; i++)	{
			Image speak = getImage(base, "images/speak/Speak_"+i+".png");
			npc.addImage(speak);
		}
	}
	
	private Image loadBackground(int i) {
		background = getImage(base, "images/background/Background_"+i+".png");
		return background;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		Sound.sound1.loop();
		while (true) {
			player.update(level);
			npc.update(player.getX());
			if (player.getEnded()) {
				level.readFile();
				level.setBackground(loadBackground(Level.getNivel()-2));
				Thread threadStory = new Thread(story);
				threadStory.start();
				player.setX(level.getStartX());
				player.setY(level.getStartY());
				player.setEnded(false);
			}
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {
		level.drawLevel(g);
		g.drawImage(player.getCurrentImage(), player.getX(), player.getY(), this);
		g.drawImage(story.getCurrentStory(), 0, 0, this);
		g.drawImage(npc.getCurrentImage(), npc.getX(), npc.getY(), this);
		g.drawImage(npc.getCurrentSpeak(), 0, 0, this);
		g.drawImage(Level.getCurrentEnd(), 0, 0, this);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if(!pause) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				Sound.jump_s.play();
				player.jump();
				player.setCurrentImage(3);
				break;
	
			case KeyEvent.VK_DOWN:
				player.setCurrentImage(4);
				if (player.isJumped() == false){
					player.setDucked(true);
					player.setSpeedX(0);
				}
				break;
	
			case KeyEvent.VK_LEFT:
				player.moveLeft();
				player.setMovingLeft(true);
				player.setCurrentImage(2);
				break;
	
			case KeyEvent.VK_RIGHT:
				player.moveRight();
				player.setMovingRight(true);
				player.setCurrentImage(1);
				break;
	
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (player.isMovingRight()) {
				player.setCurrentImage(1);
			} else if (player.isMovingLeft()) {
				player.setCurrentImage(2);
			} else {
				player.setCurrentImage(0);
			}
			break;

		case KeyEvent.VK_DOWN:
			player.setCurrentImage(0);
			player.setDucked(false);
			break;

		case KeyEvent.VK_LEFT:
			player.stopLeft();
			player.setCurrentImage(0);
			break;

		case KeyEvent.VK_RIGHT:
			player.stopRight();
			player.setCurrentImage(0);
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void setPause(boolean pause) {
		StartingClass.pause = pause;
	}
	
	public static boolean getPause() {
		return pause;
	}
	
}