package spacebitmapgame.game;

import java.util.List;

import spacebitmapgame.utils.Entity;
import spacebitmapgame.utils.OpenGLRenderer;


public class Game {
	
	public static final int WIDTH = 1024, HEIGHT = 768;
	public static List<Entity> entities;
	
	public static void main(String[] args) {
		new Boot().start();
	}
	
}