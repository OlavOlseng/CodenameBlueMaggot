

import javax.swing.JFrame;

import blueMaggot.Game;

public class derp extends JFrame {
	Game game;

	public derp() {
		setVisible(true);
		game = new Game();
		add(game);
	
	}

	public void init() {
//	game.initConnection(true);
	game.initConnection(false);
		
		// while (true)
		// System.out.println(Thread.currentThread().getThreadGroup().activeCount());
	}

	public static void main(String[] args) {
		new derp().init();
	}
}
