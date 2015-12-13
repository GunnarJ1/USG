package spacebitmapgame.utils;


import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import spacebitmapgame.game.Game;


public class OpenGLRenderer {
	
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
		
		while (!Display.isCloseRequested()) {
			//Render
			
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}
	
}
