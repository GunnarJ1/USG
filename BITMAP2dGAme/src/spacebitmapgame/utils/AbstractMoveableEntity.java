package spacebitmapgame.utils;

import java.awt.Rectangle;


public class AbstractMoveableEntity extends AbstractEntity implements MoveableEntity {

	protected float velx, vely;
	
	@Override
	public void update(int delta) {
		x += velx;
		y += vely;
	}
	
	@Override
	public void draw() {
		super.draw();
	}
	
	@Override
	public float getVelX() {
		// TODO Auto-generated method stub
		return velx;
	}

	@Override
	public float getVelY() {
		// TODO Auto-generated method stub
		return vely;
	}

	@Override
	public void setVelX(float vx) {
		this.velx = vx;
	}

	@Override
	public void setVelY(float vy) {
		this.vely = vy;
	}

	public Rectangle topHitbox() {
		int offset = (int) (x+(width/4));
		return new Rectangle(offset, (int)y, (int)width/2, (int)(height/2)/2);
	}

	public Rectangle bottomHitbox() {
		int offset = (int) (x+(width/4));
		return new Rectangle((int)offset, (int) ((int)(y+height)-(height/2)/2), (int)width/2, (int)(height/2)/2);
	}
	
	public Rectangle LeftHitbox() {
		int offset = (int) (y+(width/4));
		return new Rectangle((int)x, offset, (int)(width/2)/2, (int)height/2);
	}
	
	public Rectangle RightHitbox() {
		int offset = (int) (y+(width/4));
		return new Rectangle((int)((x+width)-(width/2)/2), offset, (int)(width/2)/2, (int)height/2);
}

	@Override
	public Rectangle hitbox() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
