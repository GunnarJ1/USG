package spacebitmapgame.game;

import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import spacebitmapgame.utils.BlockGrid;
import spacebitmapgame.utils.BlockType;
import spacebitmapgame.utils.World;

public class Boot {

	//Map
	private BlockGrid grid;
	private BlockType selectedBlock;
	
	//Quad position
	float x=100, y=100;
	//Quad angle
	float rotation=0;

	//last frame time
	long lastFrame;

	//FPS and lastFPStime
	int fps;
	long lastFPS;

	//VSync on/off
	boolean vsync;

	public void start(){
		try {
			Display.setDisplayMode(new DisplayMode(Game.WIDTH, Game.HEIGHT));
			Display.create();
		} catch (LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}

		initGL();
		init();
		
		getDelta(); //call before loop to init lastFrame
		lastFPS = getTime(); //call before loop for FPS timer
		
		while (!Display.isCloseRequested()) {
			int delta = getDelta();

			update(delta);
			renderGL();
			GL11.glColor3f(0, 0, 0);
			grid.draw();
			gameInputs();
			Display.update();
		}
		Display.destroy();
	}

	private void gameInputs() {
		int mouseX = Mouse.getX();
		int mouseY = Game.HEIGHT - Mouse.getY() - 1;

		if (Mouse.isButtonDown(0)) {
			int grid_x = Math.round(mouseX / World.BLOCK_SIZE);
			int grid_y = Math.round(mouseY / World.BLOCK_SIZE);
			grid.setAt(grid_x, grid_y, selectedBlock);
		}
		
		if (Mouse.isButtonDown(1)) {
			int grid_x = Math.round(mouseX / World.BLOCK_SIZE);
			int grid_y = Math.round(mouseY / World.BLOCK_SIZE);
			grid.setAt(grid_x, grid_y, BlockType.Air);
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			grid.save(new File("save.xml"));
		}
			
		if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
			grid.load(new File("save.xml"));
			selectedBlock = grid.getAt(0, 0).getType(); 
		}
			
		if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
			grid.clearShitOffScreen();
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
			selectedBlock = BlockType.Stone;
			grid.setAt(0, 0, selectedBlock);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
			selectedBlock = BlockType.Dirt;
			grid.setAt(0, 0, selectedBlock);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
			selectedBlock = BlockType.Grass;
			grid.setAt(0, 0, selectedBlock);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
			selectedBlock = BlockType.Random;
			grid.setAt(0, 0, selectedBlock);
		}

	}

	private void init() {
		grid = new BlockGrid();
	}

	public void update(int delta){
		rotation += .45f * delta; // rotate quad

		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			x -= .45f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
			x += .45f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_S))
			y -= .45f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
			y += .45f * delta;

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_F){
					setDisplayMode(1024, 768, !Display.isFullscreen());
				}
				else if(Keyboard.getEventKey() == Keyboard.KEY_V) {
					vsync = !vsync;
					Display.setVSyncEnabled(vsync);
				}
			}
		}

		//set quad boundaries
		if (x < 0)
			x = 0;
		if (x > Display.getDisplayMode().getWidth())
			x = Display.getDisplayMode().getWidth();
		if (y < 0)
			y = 0;
		if (y > Display.getDisplayMode().getHeight())
			y = Display.getDisplayMode().getHeight();

		updateFPS(); //Update dat FPS
	}

	public void setDisplayMode(int width, int height, boolean fullscreen){

		DisplayMode[] modes;

		//end if requested mode is already set
		if ((Display.getDisplayMode().getWidth() == width) && 
				(Display.getDisplayMode().getHeight() == height) && 
				(Display.isFullscreen() == fullscreen))
			return;

		try {
			DisplayMode targetDisplayMode = Display.getDesktopDisplayMode();

			if (fullscreen) {
				modes = Display.getAvailableDisplayModes();
				int freq=0;

				for (int i=0; i < modes.length; i++) {
					DisplayMode current = modes[i];

					if ((current.getWidth() == width) && (current.getHeight() == height)){
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq)){
							if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}
						/* if we've found a match for bpp and frequency against the 
						 ** original display mode then go for this one
						 ** since it's most likely compatible with the monitor */
						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
								(current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())){
							targetDisplayMode = current;
							break;
						}
					}
				}
				
			} else {
				targetDisplayMode = new DisplayMode(width, height);
			}

			if (targetDisplayMode == null) {
				System.out.println("Failed to find mode: " + width + " x " + height + " fullscreen=" +fullscreen);
				return;
			}

			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);
		} catch (LWJGLException e) {
			System.out.println("unable to setup mode " + width + " x " + height + " fullscreen = " + fullscreen + e);
		}
	}

	public int getDelta() {
		long time = getTime();
		int delta = (int)(time - lastFrame);
		lastFrame = time;

		return delta;
	}

	public long getTime() {
		return (Sys.getTime()*1000)/Sys.getTimerResolution();
	}

	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Game.WIDTH, Game.HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glIsEnabled(GL11.GL_TEXTURE_2D);
	}

	public void renderGL() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	public static void main(String[] argv) {
		Boot display = new Boot();
		display.start();
	}
}

