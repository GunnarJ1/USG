package spacebitmapgame.utils;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static spacebitmapgame.utils.World.BLOCK_SIZE;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public class Block {
	protected BlockType type = BlockType.Dirt;
	protected float x, y;
	protected Texture texture;

	public Block(BlockType type, float x, float y) {
		this.type = type;
		this.x = x;
		this.y = y;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + type.location + ".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Couldn't find: " + type.location);
		}
	}

	public void draw() {
		texture.bind();
		if (type != BlockType.Air)	
			GL11.glColor3f(1, 1, 1);
		else if (type == BlockType.Air)	
			GL11.glColor3f(0, 0, 0);
		
		glLoadIdentity();
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(0, 0);
			glTexCoord2f(1, 0);
			glVertex2f(BLOCK_SIZE, 0);
			glTexCoord2f(1, 1);
			glVertex2f(BLOCK_SIZE, BLOCK_SIZE);
			glTexCoord2f(0, 1);
			glVertex2f(0, BLOCK_SIZE);
		glEnd();
		glTranslatef(-x, -y, 0);
		glLoadIdentity();
	}

	public Rectangle hitbox() {
		return new Rectangle((int)x, (int)y, BLOCK_SIZE, BLOCK_SIZE);
	}
	
	public BlockType getType() {
		return type;
	}

	public void setType(BlockType type) {
		this.type = type;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
	
}
