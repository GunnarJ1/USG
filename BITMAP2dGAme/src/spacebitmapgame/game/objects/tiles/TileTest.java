package spacebitmapgame.game.objects.tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import spacebitmapgame.game.objects.ID;
import spacebitmapgame.utils.GameObject;
import spacebitmapgame.utils.SpriteSheet;

public class TileTest extends GameObject{

	BufferedImage icon = new SpriteSheet("TerrainTileSet", 32, 32).getSprite(1, 2);
		
	public TileTest(float x, float y) {
		super(x, y, ID.Tile);
		width = 32;
		height = 32;
		isSolid = true;
	}

	public void tick() {
		
	}

	public void render(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.draw(getBounds());
		g.drawImage(icon, (int)x, (int)y, null);
	}

}
