package spacebitmapgame.utils;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import spacebitmapgame.game.Game;

public class SpriteSheet {
	
	//The class of all your PNG needs
	//NOTE: This class will only handle PNGs, because found efficient in games
	
	BufferedImage sheet;
	int width, hieght;
	
	public SpriteSheet(String loc, int w, int h) {
		this.width = w;
		this.hieght = h;
		try {
			sheet = ImageIO.read(Game.class.getResourceAsStream("res/" + loc + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CAN'T FIND FILE");
		}
	}
	
	public BufferedImage getSheet() {
		return sheet;
	}
	
	public BufferedImage getSprite(int row, int col) {
		
		try {
			return sheet.getSubimage((col*hieght) - hieght, (row * width) - width, width, hieght);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	
}

