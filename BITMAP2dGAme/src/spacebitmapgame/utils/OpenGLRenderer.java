package spacebitmapgame.utils;


import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
		Game.entities = new ArrayList<Entity>();
		player = new Player(50, 50, AbstractEntity.loadTexture("image"));
		Game.entities.add(player);
		for (int i = 0; i < 20; i++) {
			Game.entities.add(new Block(i*32, 400, new Vector3f(0, 1, 1), AbstractEntity.loadTexture("image")));
		}
		
		while (!Display.isCloseRequested()) {
			//Render
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			
			for (Entity entity : Game.entities) {
				entity.update(getDelta());
				entity.draw();
			}
			
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}
	
	private void init() {
	
	}
	
	private long lastFame;
	
	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	private int getDelta() {
		long currentTime = getTime();
		int delta = (int)(currentTime - lastFame);
		lastFame = getTime();
		return delta;
	}
	
}
