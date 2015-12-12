package spacebitmapgame.utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator {
	
	private BufferedImage sprite;
	private ArrayList<BufferedImage> frames;
	private volatile boolean running = false;
	
	private long prevTime, speed;
	private int frameatPause, currentFrame;
	
	public Animator(ArrayList<BufferedImage> frames)  {
		this.frames = frames;
	}
	
	public void setSpeed(long speed) {
		this.speed = speed;
	}
	
	public void update(long time) {
		if (running) {
			if (time - prevTime >= speed) {
				currentFrame++;
				try {
					sprite = frames.get(currentFrame);
				} catch (IndexOutOfBoundsException e) {
					currentFrame = 0;
					sprite = frames.get(currentFrame);
				}
				prevTime = time;
			}
		}
	}
	
	public void play() {
		running = true;
		prevTime = 0;
		frameatPause = 0;
		currentFrame = 0;
	}
	
	public void stop() {
		running = false;
		prevTime = 0;
		frameatPause = 0;
		currentFrame = 0;
	}
	
	public void pause() {
		frameatPause = currentFrame;
		running = false;
	}
 	
	public void resume() {
		currentFrame = frameatPause;
	}
	
	public void reset() {
		currentFrame = 0;
	}
	
	public boolean isDoneAnimating() {
		if (currentFrame == frames.size()) {
			return true;
		} else {
			return false;
		}
	}
	
	public BufferedImage getSprite() { 
		return sprite;
	}
	
}
