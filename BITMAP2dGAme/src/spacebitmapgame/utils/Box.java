package spacebitmapgame.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Box {
	
	private float x, y, width, height, velx, vely; 
	private Vector3f color;
	private Texture texture;
	private int id;
	/**
	 * 
	 * @param sx Box's start X coord
	 * @param sy Box's start Y coord
	 * @param col Color of box
	 */
	public Box(float sx, float sy, Vector3f col, Texture text, int id) {
		x = sx;
		y = sy;
		width = 32;
		height = 32;
		this.id = id;
		if (text == null)	
			this.color = col;
	}
	
	public void render() {
		x += velx;
		y += vely;
		if (id == 0)
			update();
		
		GL11.glColor3f(color.x, color.y, color.z);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(x+width, y);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(x+width, y+height);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(x, y+height);
		GL11.glEnd();
		
	}
	
	public void init() {
		texture = loadTexture("image");
	}
	
	private void update() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.setVely(-2);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.setVely(2);
		} else {
			this.setVely(0);
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.setVelx(-2);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.setVelx(2);
		} else {
			this.setVelx(0);
		}
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getVelx() {
		return velx;
	}

	public void setVelx(float velx) {
		this.velx = velx;
	}

	public float getVely() {
		return vely;
	}

	public void setVely(float vely) {
		this.vely = vely;
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public Texture getTexture() {
		return texture;
	}

	public static Texture loadTexture(String path) {
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + path + ".png")));
		} catch (IOException e) {
			System.err.println("Coundn't find image: " + path);
		}
		return null;
	}
	
}
