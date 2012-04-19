package input;

import gfx.GameDebug;
import gfx.MenuTitle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

	MenuTitle menu;
	GameDebug game;

	public InputHandler(GameDebug game) {
		this.game = game;
	}
	public  InputHandler(MenuTitle menu){
		this.menu = menu;
	}

	public InputHandler() {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_W)
			System.out.println("dicks");
		if (arg0.getKeyCode() == KeyEvent.VK_A)
			System.out.println("superdicks");
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE){
			game.toggleMenu();
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_Q){
			System.exit(1);
		}
		System.out.println(arg0.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
