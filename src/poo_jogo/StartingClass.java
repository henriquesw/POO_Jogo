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
	private Graphics second;
	private URL base;
	private static Level level;

	@Override
	public void init() {

		setSize(1280, 768);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Plataforma");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Image Setups
		character = getImage(base, "images/player/Player.png");
		characterRight = getImage(base, "images/player/Player_Direita.gif");
		characterLeft = getImage(base, "images/player/Player_Esquerda.gif");
		characterDown = getImage(base, "images/player/Player_4.png");
		characterJumped = getImage(base, "images/player/Player_Pulando.png");
		currentSprite = character;
		background = getImage(base, "images/background/Background.png");
	}

	@Override
	public void start() {
		level = new Level(background);
		player = new Player();
		
		for(int i = 0; i <= 2; i++) {
			Image image = getImage(base, "images/tiles/tile_"+i+".png");
			Tile tile = new Tile(image);
			level.addTile(tile);
		}
		
		level.readFile();
		player.setX(level.getStartX());
		player.setY(level.getStartY());

		Thread thread = new Thread(this);
		thread.start();
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
		while (true) {
			player.update(level);
			if (player.getEnded()) {
				level.readFile();
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
		g.drawImage(currentSprite, player.getX(), player.getY(), this);

	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			player.jump();
			currentSprite = characterJumped;
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			if (player.isJumped() == false){
				player.setDucked(true);
				player.setSpeedX(0);
			}
			break;

		case KeyEvent.VK_LEFT:
			player.moveLeft();
			player.setMovingLeft(true);
			currentSprite = characterLeft;
			break;

		case KeyEvent.VK_RIGHT:
			player.moveRight();
			player.setMovingRight(true);
			currentSprite = characterRight;
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (player.isMovingRight()) {
				currentSprite = characterRight;
			} else if (player.isMovingLeft()) {
				currentSprite = characterLeft;
			} else {
				currentSprite = character;
			}
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = character;
			player.setDucked(false);
			break;

		case KeyEvent.VK_LEFT:
			player.stopLeft();
			currentSprite = character;
			break;

		case KeyEvent.VK_RIGHT:
			player.stopRight();
			currentSprite = character;
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static Level getBg1() {
		return level;
	}

}