package spacebitmapgame.utils;

import java.awt.Rectangle;

import spacebitmapgame.game.Game;

public class Check {
	
	public static boolean collionBlock(Rectangle hitbox) {
		for (Entity entity : Game.entities) {
			AbstractEntity en = (AbstractEntity) entity;
			if (en.isSolid()) {
				if (en.getHitbox().intersects(hitbox) || hitbox.intersects(en.getHitbox())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static Rectangle collidedBlock(Rectangle hitbox) {
		for (Entity entity : Game.entities) {
			AbstractEntity en = (AbstractEntity) entity;
			if (en.isSolid()) {
				if (en.getHitbox().intersects(hitbox) || hitbox.intersects(en.getHitbox())) {
					return en.getHitbox();
				}
			}
		}
		return null;
	}
	
}
