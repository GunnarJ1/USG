package spacebitmapgame.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.PNGDecoder;

public class BitmapFonts {
	
	// The integer is an ID that reads from the instance of openGL
	public static int MainGameFont;
	
	public BitmapFonts () {
		
	}

	public static void setupTextures() {
		// Creates a new texture from the bitmap textures
		MainGameFont = GL11.glGenTextures();
		// Binds the texture of font to a texture 2D format
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, MainGameFont);
		
		// To load PNG file
		PNGDecoder decoder = null;
		try {
			decoder = new PNGDecoder(new FileInputStream(("res/font.png")));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
		try {
			decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.RGBA);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		buffer.flip();
		
		// Loads the previously loaded texture data into the texture object
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_2D, decoder.getWidth(), decoder.getHeight(), 
				0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
	}
	
}
