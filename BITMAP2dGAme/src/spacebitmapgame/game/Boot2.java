package spacebitmapgame.game;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import spacebitmapgame.utils.BlockGrid;
import spacebitmapgame.utils.BlockType;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Boot2 {

	//Map
	private BlockGrid grid;
	private BlockType selectedBlock = BlockType.Air;

	// We need to strongly reference callback instances.
	private GLFWErrorCallback errorCallback;
	private GLFWKeyCallback   keyCallback;

	double lastFPS;
	double lastFrame;
	int fps;
	
	// The window handle
	private long window;

	private int WIDTH;
	private int HEIGHT;
	private boolean FULLSCREEN;

	public Boot2(){
		this.WIDTH = 1080;
		this.HEIGHT = 720;
		this.FULLSCREEN = false;
	}

	public Boot2(boolean fullscreen){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		this.WIDTH = gd.getDisplayMode().getWidth();
		this.HEIGHT = gd.getDisplayMode().getHeight();
		this.FULLSCREEN = fullscreen;
	}

	public void run() {
		System.out.println("LWJGL " + Version.getVersion() + " FULLSCREEN " + FULLSCREEN);

		try {
			initGL();
			init();
			getDelta(); //call before loop to init lastFrame
			lastFPS = getTime(); //call before loop for FPS timer
			loop();

			// Release window and window callbacks
			glfwDestroyWindow(window);
			keyCallback.release();
		} finally {
			// Terminate GLFW and release the GLFWErrorCallback
			glfwTerminate();
			errorCallback.release();
		}
	}

	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( glfwInit() != GLFW_TRUE )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure our window
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation

		// Create the window
		if (FULLSCREEN == true)
			window = glfwCreateWindow(WIDTH, HEIGHT, "USG", glfwGetPrimaryMonitor(), NULL);
		else{
			window = glfwCreateWindow(WIDTH, HEIGHT, "USG", NULL, NULL);
			glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
			// Center our window
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(
					window,
					(vidmode.width() - WIDTH) / 2,
					(vidmode.height() - HEIGHT) / 2
					);
		}
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
					glfwSetWindowShouldClose(window, GLFW_TRUE); // We will detect this in our rendering loop
			}
		});

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
	}
	
	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0D, Game.WIDTH, Game.HEIGHT, 0.0D, 1D, -1D);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glIsEnabled(GL11.GL_TEXTURE_2D);
	}
	
	public void renderGL() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	public int getDelta() {
		double time = getTime();
		int delta = (int)(time - lastFrame);
		lastFrame = time;

		return delta;
	}
	public double getTime() {
		return GLFW.glfwGetTime();
	}
	
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			System.out.println(fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	private void update(int delta){
		updateFPS();
	}

	private void loop() {

		GL.createCapabilities();

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( glfwWindowShouldClose(window) == GLFW_FALSE ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

			glfwSwapBuffers(window); // swap the color buffers
			
			int delta = getDelta();
			update(delta);
			renderGL();
			GL11.glColor3f(0, 0, 0);
			grid.draw();

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		}

	}

	public static void main(String[] args) {
		new Boot2().run();
	}

}