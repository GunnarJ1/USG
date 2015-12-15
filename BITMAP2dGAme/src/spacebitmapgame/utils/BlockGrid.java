package spacebitmapgame.utils;

import static spacebitmapgame.utils.World.MAP_HEIGHT;
import static spacebitmapgame.utils.World.MAP_WIDTH;;

public class BlockGrid {
	
	private Block[][] blocks = new Block[MAP_WIDTH][MAP_HEIGHT];
	
	public BlockGrid() {
		for (int x = 0; x < MAP_WIDTH - 1; x++) {
			for (int y = 0; y < MAP_HEIGHT - 1; y++) {
				blocks[x][y] = new Block(BlockType.Air, x * World.BLOCK_SIZE, y * World.BLOCK_SIZE);
			}
		}
	}
	
	public void setAt(int x, int y, BlockType b) {
		blocks[x][y] = new Block(b, x*32, y*32);
	}
	
	public Block getAt(int x, int y) {
		return blocks[x*32][y*32];
	}
	
	public void draw() {
		for (int x = 0; x < MAP_WIDTH - 1; x++) {
			for (int y = 0; y < MAP_HEIGHT - 1; y++) {
				blocks[x][y].draw();
			}
		}
	}
	
}
