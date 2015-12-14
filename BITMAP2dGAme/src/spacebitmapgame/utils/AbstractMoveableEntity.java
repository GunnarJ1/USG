package spacebitmapgame.utils;


public class AbstractMoveableEntity extends AbstractEntity implements MoveableEntity {

	protected float velx, vely;
	
	@Override
	public void update(int delta) {
	
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



}
