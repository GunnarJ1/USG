package spacebitmapgame.utils;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public abstract class AbstractEntity implements Entity{
	
	protected Rectangle hitbox;
	protected float x, y, width, height; 
	protected Texture texture;
	protected Vector3f color;
	protected int id;
	protected boolean isSolid;
	
	public AbstractEntity() {
	
	}
	
	public void update(int delta) {
		
	}
	
	public void draw() {

	}
	
	public void init() {
	
	}
	
	public void isSolid(boolean is) {
		isSolid = is;
	}
	
	public boolean isSolid() {
		return this.isSolid;
	}
	
	public Rectangle getHitbox() {
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
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

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public Texture getTexture() {
		return texture;
	}
	
	protected void setTexture(Texture text) {
		this.texture = text;
	}
	
	@Override
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public static Texture loadTexture(String path) {
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + path + ".png")));
		} catch (IOException e) {
			System.err.println("Coundn't find image: " + path);
		}
		return null;
	}
	
	@Override
	public boolean intersects(Entity other) {
		hitbox.setBounds((int)x, (int)y, (int)width, (int)height);
		return  hitbox.intersects(other.getX(), other.getY(), other.getWidth(), other.getHeight());
	}
}
