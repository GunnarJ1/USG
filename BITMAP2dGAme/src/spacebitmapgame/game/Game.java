package spacebitmapgame.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import spacebitmapgame.game.objects.Player;
import spacebitmapgame.game.objects.tiles.TileTest;
import spacebitmapgame.utils.GameObject;
import spacebitmapgame.utils.InputHandler;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static InputHandler handler;
	
	public static ArrayList<GameObject> objectList = new ArrayList<GameObject>();
//	public static 
	
	public static final int GAME_WIDTH = 426;
	public static final int GAME_HEIGHT = 240;
	public static final int SCALE = 2;
	public static String NAME = "TEST ENGINE";
	public boolean running = false;
	//Window
	private JFrame frame;
	//Thread for game
	private Thread thread;
	
	
	//Images
//	SpriteSheet ss = new SpriteSheet("/res/pistle", 32, 32);
	//Objects
	
	public Game() {
		setMinimumSize(new Dimension(GAME_WIDTH*SCALE, GAME_HEIGHT*SCALE));
		setMaximumSize(new Dimension(GAME_WIDTH*SCALE, GAME_HEIGHT*SCALE));
		setPreferredSize(new Dimension(GAME_WIDTH*SCALE, GAME_HEIGHT*SCALE));
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		handler = new InputHandler(this);
		for (int i = 0; i < 20; i++) {
			objectList.add(new TileTest(i*32, 200));
		}
		objectList.add(new TileTest(20*32, 200+32));
		objectList.add(new TileTest(20*32+32, 200+32));
		objectList.add(new TileTest(20*32+64, 200+32));
		objectList.add(new TileTest(0, 200-32));
		objectList.add(new TileTest(128, 200-32));
		
		objectList.add(new Player());
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Game loader
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: "+ frames );
				frames = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		for (GameObject temp : objectList) {
			temp.tick();
		}
	}
	
	public void render() {
		//A frame buffer loader
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		//Creates a graphics component to draw
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		//start to render objects
		for (GameObject temp : objectList) {
			temp.render(g);
		}
		//end to render objects
		//Dispose of extra bites
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g.setRenderingHints(rh);
		g.dispose();
		//Shows the graphics
		bs.show();
	}
	
	public static void main(String[] args) {
		new Game().start();
	}
	
}