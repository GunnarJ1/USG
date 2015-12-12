package spacebitmapgame.game.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import spacebitmapgame.utils.GameObject;

public class Entity extends GameObject {

	public Entity(float x, float y, ID id) {
		super(x, y, id);
	}

	public Rectangle getTopBounds() {
		return new Rectangle((int)x+(int)width/2/2/2, (int)y, (int)width-(int)width/2/2, (int)height/2/2/2);
	}
	
	public Rectangle getBottomBounds() {
		return new Rectangle((int)x+(int)width/2/2/2, (int)y+height-(height/2/2/2), (int)width-(int)width/2/2, (int)height/2/2/2);
		
	}

	public Rectangle getLeftBounds() {
		return new Rectangle((int)x, (int)y+height/2/2/2, (int)width/2/2/2, (int)height-height/2/2);
		
	}

	public Rectangle getRightBounds() {
		return new Rectangle((int)x+width-(width/2/2/2), (int)y+height/2/2/2, (int)width/2/2/2, (int)height-height/2/2);
		
	}

	
	public void tick() {
		
	}

	public void render(Graphics2D g) {
		g.setColor(Color.red);
		g.draw(getBottomBounds());
		g.setColor(Color.cyan);
		g.draw(getTopBounds());
		g.setColor(Color.yellow);
		g.draw(getLeftBounds());
		g.setColor(Color.green);
		g.draw(getRightBounds());
		
	}

}
