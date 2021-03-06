package spacebitmapgame.utils;

import static spacebitmapgame.utils.World.MAP_HEIGHT;
import static spacebitmapgame.utils.World.MAP_WIDTH;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.lwjgl.opengl.GL11;

import spacebitmapgame.game.Game;


public class BlockGrid {
	
	private Block[][] blocks = new Block[MAP_WIDTH][MAP_HEIGHT];
	
	public BlockGrid(File loadFile) {
		load(loadFile);
	}
	
	public BlockGrid() {
		for (int x = 0; x < MAP_WIDTH - 1; x++) {
			for (int y = 0; y < MAP_HEIGHT - 1; y++) {
				blocks[x][y] = new Block(BlockType.Air, x * World.BLOCK_SIZE, y * World.BLOCK_SIZE);
			}
		}
//		System.out.println("Finished task.");
	}
	
	public void clearOffScreen() {
		for (int x = 0; x < MAP_WIDTH - 1; x++) {
			for (int y = 0; y < MAP_HEIGHT - 1; y++) {
				blocks[x][y] = new Block(BlockType.Air, x * World.BLOCK_SIZE, y * World.BLOCK_SIZE);
			}
		}
		System.out.println("Finished task.");
	}
	
	public void load(File loadFile) {
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		try {
			document = builder.build(loadFile);
		} catch (JDOMException | IOException e) {
			System.out.println(e.getMessage());
			return;
		}
		Element root = document.getRootElement();
		for (Object block : root.getChildren()) {
			Element e = (Element) block;
			int x = Integer.parseInt(e.getAttributeValue("x")) / World.BLOCK_SIZE;
			int y = Integer.parseInt(e.getAttributeValue("y")) / World.BLOCK_SIZE;
			blocks[x][y] = new Block(BlockType.valueOf(e.getAttributeValue("type")),
					x * World.BLOCK_SIZE, y * World.BLOCK_SIZE);
		}
		System.out.println("Finished task.");
	}
	
	public void save(File saveFile) {
		Document document = new Document();
		Element root = new Element("blocks");
		document.setRootElement(root);
		for (int x = 0; x < MAP_WIDTH - 1; x++) {
			for (int y = 0; y < MAP_HEIGHT - 1; y++) {
				Element block = new Element("block");
				block.setAttribute("x", String.valueOf((int)blocks[x][y].getX()));
				block.setAttribute("y", String.valueOf((int)blocks[x][y].getY()));
				block.setAttribute("type", String.valueOf(blocks[x][y].getType()));
				root.addContent(block);
			}
		}
		XMLOutputter outputter = new XMLOutputter();
		try {
			outputter.output(document, new FileOutputStream(saveFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finished task.");
	}
	
	public void setAt(int x, int y, BlockType b) {
		blocks[x][y] = new Block(b, x * World.BLOCK_SIZE, y * World.BLOCK_SIZE);
	}
	
	public Block getAt(int x, int y) {
		return blocks[x*World.BLOCK_SIZE][y*World.BLOCK_SIZE];
	}
	
	public void draw() {
		GL11.glClearColor(.3f, .3f, .3f, 0);
		for (int x = 0; x < MAP_WIDTH - 1; x++) {
			for (int y = 0; y < MAP_HEIGHT - 1; y++) {
				Block block = blocks[x][y];
				if (block.getX() >= 0 && block.getX() <= Game.WIDTH && block.getY() >= 0 && block.getY() <= Game.HEIGHT)	
					block.draw();
			}
		}
	}
	
}
