package spacebitmapgame.utils;


import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import spacebitmapgame.game.Game;
import spacebitmapgame.game.objects.Player;


public class OpenGLRenderer {

	private Player player;
	
	public OpenGLRenderer(int w, int h, String title) {
		try {
			Display.setDisplayMode(new DisplayMode(w, h));
			Display.setTitle(title);
			Display.create();
		} catch (LWJGLException e) {
			System.out.println(e.getMessage());
		}
		
		//Initialization event for OpenGL
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Game.WIDTH, Game.HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glIsEnabled(GL11.GL_TEXTURE_2D);
		
		//Initializes games variables
		init();
		
		player = new Player(50, 50);
		
		while (!Display.isCloseRequested()) {
			//Render
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			
			player.draw();
			
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}
	
	private void init() {
	}
	
	
	
}
