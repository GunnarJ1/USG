package spacebitmapgame.utils;

import java.awt.Rectangle;

import spacebitmapgame.game.Game;

public class Check {
	
	public static boolean CollisionBlock(Rectangle rect) {
		for (GameObject object : Game.objectList) {
			if (object.isSolid()) {
				if (object.getBounds().intersects(rect) || rect.intersects(object.getBounds())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static Rectangle CollidingBlock(Rectangle rect) {
		for (GameObject object : Game.objectList) {
			if (object.isSolid()) {
				if (object.getBounds().intersects(rect) || rect.intersects(object.getBounds())) {
					return object.getBounds();
				}
			}
		}
		return null;
	}
	
}
