package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_W)
			System.out.println("dicks");
		if(arg0.getKeyCode() == KeyEvent.VK_A)
			System.out.println("superdicks");
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(1);
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
