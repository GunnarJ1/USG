package spacebitmapgame.utils;

import org.newdawn.slick.opengl.Texture;

public interface Entity {
	public void init();
	public void draw();
	public void update(int delta);
	public void setLocation(float x, float y);
	public void setWidth(float width);
	public void setHeight(float height);
	public Texture getTexture();
	public float getX();
	public float getY();
	public float getWidth();
	public float getHeight();
	public boolean intersects(Entity other);

}