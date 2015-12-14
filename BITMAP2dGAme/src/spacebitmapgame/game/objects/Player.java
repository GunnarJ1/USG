package spacebitmapgame.game.objects;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import spacebitmapgame.utils.AbstractMoveableEntity;


public class  Player extends AbstractMoveableEntity {
	
	Texture texture;
	
	public Player(float x, float y) {
		setX(x);
		setY(y);
		setWidth(32);
		setHeight(32);
		texture = loadTexture("image");
	}
	
	@Override
	public void draw() {
	GL11.glColor3f(1, 1, 1);
	GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x+width, y);
		GL11.glVertex2f(x+width, y+height);
		GL11.glVertex2f(x, y+height);
	GL11.glEnd();
	}
	
}
