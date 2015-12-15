package spacebitmapgame.game.objects;

import java.awt.Rectangle;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import spacebitmapgame.game.Game;
import spacebitmapgame.utils.AbstractMoveableEntity;
import spacebitmapgame.utils.Check;


public class  Player extends AbstractMoveableEntity {
	
	// Player fields
	private float gravity = .05f;
	private float terminalVertVel = 15;
	private boolean isJumping;
	private int jumpHeight;
	private int jumpSpeed;
	private int startJumpY;
	
	public Player(float x, float y, Texture text) {
		setX(x);
		setY(y);
		setWidth(32);
		setHeight(32);
		texture = text;
		System.out.println(texture.getTextureID());
		isJumping = false;
		jumpHeight = 64;
		jumpSpeed = 14;
	}
	
	@Override
	public void update(int delta) {
		super.update(delta);
		if (y-vely >= Game.HEIGHT)
			System.exit(-1);
		
		inputs();
		collisionCheck();
	}
	
	public void draw() {
		texture.bind();
		GL11.glColor3f(1f, 0, 0);
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
		
		
		if (!isJumping && vely == 0 && Keyboard.isKeyDown(Keyboard.KEY_SPACE) && Check.collionBlock(bottomHitbox()) ){ 
			startJumpY = (int) y;
			vely += -jumpSpeed;
			isJumping = true;
		}
		
		if (!isJumping) {	
			if (vely == 0) vely = .5f;
			if (vely < terminalVertVel)	
				vely = gravity * vely;
			else
				vely = terminalVertVel;
			System.out.println(vely);
		}
		
//		System.out.println(isJumping);
		if (isJumping) {
			if (y < startJumpY-jumpHeight) {
				vely = 0;
				isJumping = false;
			} else {
				vely += .15f;
			}
		}
	}


		
	
	
	private void collisionCheck() {
		if (Check.collionBlock(bottomHitbox())) {
			Rectangle temp = Check.collidedBlock(this.bottomHitbox());
			if (y+height < temp.getY()+3) {
				y = (int)temp.getY()+2-height;
			} else {
				y = (int)temp.getY()+2-height;
			}
			vely = 0;
		}
	}
}
