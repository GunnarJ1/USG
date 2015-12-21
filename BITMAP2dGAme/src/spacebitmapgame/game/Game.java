package spacebitmapgame.game;

import java.util.List;

import spacebitmapgame.utils.Entity;
import spacebitmapgame.utils.OpenGLRenderer;


public class Game {
	
	public static final int SCALE = 2;
	public static final int WIDTH = 800/2 * SCALE, HEIGHT = (600/2 * SCALE);
	public static List<Entity> entities;
	
	public static void main(String[] args) {
		new Boot().start();
	}
	
}