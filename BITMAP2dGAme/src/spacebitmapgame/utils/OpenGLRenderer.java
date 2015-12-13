package spacebitmapgame.utils;


import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import spacebitmapgame.game.Game;


public class OpenGLRenderer {
	private List<Box> boxes = new ArrayList<Box>();
	
	//Box (x, y, color vector, texture)
	private Box box = new Box(400, 300, new Vector3f(1, 1, 1), null, 0);
	private Box box2 = new Box(100, 100, new Vector3f(.9f, 0f, 0f), null, 1);
	
	public OpenGLRenderer(int w, int h, String title) {
		boxes.add(box);
		boxes.add(box2);
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
		
		for (Box b : boxes) {
			b.init();
		}
		
		while (!Display.isCloseRequested()) {
			//Render
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			
			for (Box b : boxes) {
				b.getTexture().bind();
				b.render();
			}
			
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}
	
}
