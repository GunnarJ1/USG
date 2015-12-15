package spacebitmapgame.game;

import java.util.List;

import spacebitmapgame.utils.Entity;
import spacebitmapgame.utils.OpenGLRenderer;


public class Game {
	
	public static final int WIDTH = 856, HEIGHT = 480;
	public static List<Entity> entities;
	
	public static void main(String[] args) {
		new Boot(856, 480, "TEST ENGINE");
	}
	
}