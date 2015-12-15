package spacebitmapgame.game;


import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glIsEnabled;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import spacebitmapgame.utils.BlockGrid;
import spacebitmapgame.utils.BlockType;
import spacebitmapgame.utils.World;


public class Boot {

//	private Player player;
	private BlockGrid grid;
	public Boot(int w, int h, String title) {
		try {
			Display.setDisplayMode(new DisplayMode(w, h));
			Display.setTitle(title);
			Display.create();
		} catch (LWJGLException e) {
			System.out.println(e.getMessage());
		}
		
		//Initialization event for OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Game.WIDTH, Game.HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glIsEnabled(GL_TEXTURE_2D);
		grid = new BlockGrid();

		//Initializes games variables
		init();
		
//		grid.setAt(10, 10, BlockType.Dirt);
//		grid.setAt(11, 10, BlockType.Stone);
//		grid.setAt(12, 10, BlockType.Grass);
		while (!Display.isCloseRequested()) {
			//Render
			glClear(GL_COLOR_BUFFER_BIT);

			grid.draw();
			inputs();
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
	
	private void inputs() {
		int mouseX = Mouse.getX();
		int mouseY = Game.HEIGHT - Mouse.getY() - 1;

		if (Mouse.isButtonDown(0)) {
			int grid_x = Math.round(mouseX / World.BLOCK_SIZE);
			int grid_y = Math.round(mouseY / World.BLOCK_SIZE);
			grid.setAt(grid_x, grid_y, BlockType.Grass);
			System.out.println("" + grid_x);
		}
		
		if (Mouse.isButtonDown(1)) {
			int grid_x = Math.round(mouseX / World.BLOCK_SIZE);
			int grid_y = Math.round(mouseY / World.BLOCK_SIZE);
			grid.setAt(grid_x, grid_y, BlockType.Dirt);
		}
		
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_S) {
				grid.save(new File("save.xml"));
			}
			
			if (Keyboard.getEventKey() == Keyboard.KEY_L) {
				grid.load(new File("save.xml"));
			}
		}
	}
	
}

