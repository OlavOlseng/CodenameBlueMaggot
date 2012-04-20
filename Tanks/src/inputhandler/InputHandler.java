package inputhandler;

import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class InputHandler implements KeyListener{

	private List<Key> keys = new ArrayList<Key>();
	
	private Key menu = new Key(keys);
	private Key up1 = new Key(keys);
	private Key down1 = new Key(keys);
	private Key left1 = new Key(keys);
	private Key right1 = new Key(keys);
	private Key fire1 = new HeldKey(keys, 100);
	private Key rotateR1 = new Key(keys);
	private Key rotateL1 = new Key(keys);
	
	private Key up2 = new Key(keys);
	private Key down2 = new Key(keys);
	private Key left2 = new Key(keys);
	private Key right2 = new Key(keys);
	private Key fire2 = new HeldKey(keys, 100);
	private Key rotateR2 = new Key(keys);
	private Key rotateL2 = new Key(keys);

	public void releaseAll() {
		for (Key key : keys) {
			key.down = false;
		}
	}
	
	public void tick(){
		for (Key key : keys) {
			key.tick();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		toggle(ke, true);
	}
	
	@Override
	public void keyReleased(KeyEvent ke) {
		toggle(ke, false);
	}
	
	public void toggle(KeyEvent e, boolean pressed){
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) menu.toggle(pressed);
		
		if (e.getKeyCode() == KeyEvent.VK_W) up1.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_S) down1.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_A) left1.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_D) right1.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_TAB) fire1.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_E) rotateR1.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_Q) rotateL1.toggle(pressed);
		
		if (e.getKeyCode() == KeyEvent.VK_UP) up2.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_DOWN) down2.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_LEFT) left2.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) right2.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_ENTER) fire2.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_E) rotateR2.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_Q) rotateL2.toggle(pressed);
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	
}
