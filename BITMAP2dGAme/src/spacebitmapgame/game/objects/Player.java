package spacebitmapgame.game.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import spacebitmapgame.game.Game;
import spacebitmapgame.utils.Check;
import spacebitmapgame.utils.InputHandler;
import spacebitmapgame.utils.SpriteSheet;

public class Player extends Entity {

	// TODO FIX JUMPING
	
	private int termVel = 12; //Fastest velocity falling
	private int termVelx = 4; //Fastest speed for X velocity
	private int direction = 0;
	
	private int jumpSpeed; //The velocity of the jump
	private int startJumpY; //The position jumping from
	private int jumpHeight; //The max height of jump
	private boolean isJumping = false; //Tells whether the player is jumping or not
	
	private InputHandler keyHandler = Game.handler; //The key input detector
	private BufferedImage icon = new SpriteSheet("CharacterTileSet (1)", 0, 0).getSheet(); //Player image
	
	public Player() {
		super(80, 10, ID.Player);
		width = 32;
		height = 64;
		gravity = 0.08f;
		vely = .1f;
		jumpHeight = 64;
		jumpSpeed = 4;
	}
	
	public void tick() {
		super.tick();
		//Gravity
		//Physics Check
		checks();
		
		if (direction == 1)
			x += velx;
		
		if (direction == 2)
			x += -velx;
		
		y += vely;
		
	}

	public void render(Graphics2D g) {
		g.setColor(Color.blue);
		g.draw(getBounds());
		g.drawImage(icon, (int)x, (int)y, null);
		g.drawString(""+velx, 10, 10);
		super.render(g);
	}

	private void checks() {
		//Gravity
		if (!isJumping) {	
			if (vely == 0 ) vely = .5f;
			if (vely < termVel)	
				vely += gravity * vely;
			else
				vely = termVel;
		}
		
		if (isJumping) {
			if (y < startJumpY-jumpHeight) {
				vely = 0;
				isJumping = false;
			} else if (Check.CollisionBlock(getBottomBounds()) || Check.CollisionBlock(getTopBounds())){
				vely = 0;
				isJumping = false;
			} else {
				vely += .15f;
			}
		}
		
		//Collision
		if (Check.CollisionBlock(getBottomBounds())) {
			vely = 0;			
			int tempY = (int)Check.CollidingBlock(getBottomBounds()).getY();
			if (y+height > tempY+2) {
				y = tempY-height;
			}
		}
		
		if (Check.CollisionBlock(getLeftBounds())) {
			Rectangle temp = Check.CollidingBlock(getLeftBounds());
			int rx = (int) temp.getX();
			int rw = (int) temp.getWidth();
			if (x < (rw + rx) - 1) {
				x = rx + rw;
				velx = 0;
			}
		}
		
		if (Check.CollisionBlock(getRightBounds())) {
			Rectangle temp = Check.CollidingBlock(getRightBounds());
			int rx = (int) temp.getX();
			if (x+width > rx + 1) {
				velx = 0;
				x = rx-width;
			}
		}
		
		//--Ending of collision
		
		//Inputs
		if (!isJumping && vely == 0 && keyHandler.isKeyDown(KeyEvent.VK_SPACE) && Check.CollisionBlock(getBottomBounds())) { 
			startJumpY = (int)y;
			vely += -jumpSpeed;
			isJumping = true;
		}
		

		
		if (keyHandler.isKeyDown(KeyEvent.VK_D) && !Check.CollisionBlock(getRightBounds())) {
			if (velx == 0) {
				velx = .18f;
			}
			if (velx < termVelx)
				velx += .18f * velx;
			else
		 		velx = termVelx;
			direction = 1;
		} 

		if (keyHandler.isKeyDown(KeyEvent.VK_A) && !Check.CollisionBlock(getLeftBounds())) {
			if (velx == 0) {
				velx = .18f;
			} 

			if (velx < termVelx)
				velx += .18f * velx;
			else
				velx = termVelx;
			direction = 2;
		} 
		
		if (!keyHandler.isKeyDown(KeyEvent.VK_D) && !keyHandler.isKeyDown(KeyEvent.VK_A)) {
			if (velx >= .1f)
				velx -= .05f * velx;
			if (velx <= .1f) {
				velx = 0;
				direction = 0;
			}
		}
		
		
	}
	
}
