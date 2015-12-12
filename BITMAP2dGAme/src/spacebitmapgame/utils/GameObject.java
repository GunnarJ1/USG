package spacebitmapgame.utils;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import spacebitmapgame.game.objects.ID;

public abstract class GameObject {
	
	protected boolean isSolid;
	protected Rectangle rect;
	protected float x, y, velx, vely, gravity;
	protected int width, height;
	protected ID id;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g);

	public Rectangle getBounds() {
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

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public ID getId() {
		return id;
	}
	
	public GameObject isSolid(boolean is) {
		this.isSolid = is;
		return this;
	}
	
	public boolean isSolid() {
		return this.isSolid;
	}
	
}
