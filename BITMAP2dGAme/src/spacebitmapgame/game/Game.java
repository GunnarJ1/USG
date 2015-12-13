package spacebitmapgame.game;

import spacebitmapgame.utils.OpenGLRenderer;


public class Game {
	
	public static final int WIDTH = 856, HEIGHT = 480;
	
	public static void main(String[] args) {
		new OpenGLRenderer(856, 480, "TEST ENGINE");
	}
	
}