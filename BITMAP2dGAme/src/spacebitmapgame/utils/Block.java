package spacebitmapgame.utils;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

public class Block extends AbstractEntity {
	
	private Vector3f color;
	
	public Block (float x, float y, Vector3f color, Texture texture) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.texture = texture;
		width = 32;
		height = 32;
	}
	
	@Override
	public void draw() {
		texture.bind();
		GL11.glColor3f(color.x, color.y, color.z);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(x+width, y);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(x+width, y+height);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(x, y+height);
		GL11.glEnd();
	}
	
}
