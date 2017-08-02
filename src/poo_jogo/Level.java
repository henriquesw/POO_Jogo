package poo_jogo;

import java.awt.Graphics;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
	
	private int bgX, bgY;
	private Image background;
	int i = 1;
	private int matriz[][] = new int[12][20];
	
	private ArrayList<Tile> tiles = new ArrayList<>();
	
	public Level(Image bg){
		background = bg;
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
	
	public void addTile(Tile tile) {
		tiles.add(tile);
	}
	
	public int[][] getMatriz() {
		return matriz;
	}
	
	public void readFile() {
		try {
			Scanner ler = new Scanner(new FileReader("levels/Level_1.map"));
			for(int i = 0; i < 12; i++)
				for(int j = 0; j < 20; j++)
					matriz[i][j] = (int) ler.nextInt();
			ler.close(); 
		} catch (FileNotFoundException e) {
			
		}
	}
	
	public void drawLevel(Graphics g) {
		g.drawImage(background, 0, 0, null);
		for(int i = 0; i < 12; i++)
			for(int j = 0; j < 20; j++)
				g.drawImage(tiles.get(matriz[i][j]).getImage(), j*64, i*64, null);
	}
	
}
