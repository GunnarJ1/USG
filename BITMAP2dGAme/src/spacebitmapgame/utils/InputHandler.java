package spacebitmapgame.utils;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputHandler {
	
	public static InputHandler handler;
	boolean[] keys = new boolean[256];
	boolean[] mouse = new boolean[4];
	MouseEvent[] mEvent = new MouseEvent[4];
	
	
	public InputHandler(Component c) {
		c.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			
			public void keyReleased(KeyEvent e) {
				keys[e.getKeyCode()] = false;
			}

			public void keyPressed(KeyEvent e) {
				keys[e.getKeyCode()] = true;
			}
		});
		
		c.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				mouse[e.getButton()] = false;
				mEvent[e.getButton()] = e;
			}
			
			public void mousePressed(MouseEvent e) {
				mouse[e.getButton()] = true;
				mEvent[e.getButton()] = e;
				
			}
			
			public void mouseExited(MouseEvent e) {}
			
			public void mouseEntered(MouseEvent e) {}
			
			public void mouseClicked(MouseEvent e) {}
		});
		
		handler = this;
	}
	
	public boolean isKeyDown(int keyCode) {
		if(keyCode > 0 && keyCode < keys.length) {
			return keys[keyCode];
		}
		return false;
	}
	
	public boolean isMouseDown(int button) {
		if(button > 0 && button <= 3) {
			return mouse[button];
		}
		return false;
	}
	
	public MouseEvent getEvent(int event) {
		if(event > 0 && event <= 3) {
			return mEvent[event];
		}
		return mEvent[event];
	}
	
	
}
