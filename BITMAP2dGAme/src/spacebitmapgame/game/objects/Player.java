package spacebitmapgame.game.objects;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import spacebitmapgame.game.Game;
import spacebitmapgame.utils.AbstractMoveableEntity;


public class  Player extends AbstractMoveableEntity {
	
	private float gravity = .05f;
	private float terminalVertVel = 15;
	public Player(float x, float y, Texture text) {
		setX(x);
		setY(y);
		setWidth(32);
		setHeight(32);
		texture = text;
		System.out.println(texture.getTextureID());
		vely = .00005f;
	}
	
	@Override
	public void update(int delta) {
		vely += vely * gravity;
		if (vely >= terminalVertVel)
			vely = terminalVertVel;
		
		if (y-vely >= Game.HEIGHT)
			System.exit(-1);
		
		inputs();
		super.update(delta);
	}
	
	public void draw() {
		texture.bind();
		GL11.glColor3f(1f, 1f, 0);
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
	
	@Override
	public Texture getTexture() {
		return texture;
	}
	
	private void inputs() {
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			velx = -2;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			velx = 2;
		} else {
			velx = 0;
		}
		
	}
	
	private void collisionCheck() {
		
	}
}
