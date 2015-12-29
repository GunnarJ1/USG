package spacebitmapgame.game;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import spacebitmapgame.utils.BlockGrid;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
 
public class WindowClass {
 
	public BlockGrid grid  = new BlockGrid();
	
    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;
 
    // The window handle
    private long window;
    
    private int WIDTH;
    private int HEIGHT;
    private boolean FULLSCREEN;
    
    public WindowClass(){
    	this.WIDTH = 1080;
    	this.HEIGHT = 720;
    	this.FULLSCREEN = false;
    }
    
    public WindowClass(boolean fullscreen){
    	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    	this.WIDTH = gd.getDisplayMode().getWidth();
    	this.HEIGHT = gd.getDisplayMode().getHeight();
    	this.FULLSCREEN = fullscreen;
    }
 
    public void run() {
        System.out.println("LWJGL " + Version.getVersion() + " FULLSCREEN " + FULLSCREEN);
 
        try {
            init();
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
 
    private void loop() {
 
    	GL.createCapabilities();
 
        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
 
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( glfwWindowShouldClose(window) == GLFW_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            
            glfwSwapBuffers(window); // swap the color buffers
            grid.draw();
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
        
    }
 
    public static void main(String[] args) {
        new WindowClass(true).run();
    }
 
}