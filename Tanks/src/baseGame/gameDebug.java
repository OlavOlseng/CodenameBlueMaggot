package baseGame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class gameDebug extends JFrame {
	public gameDebug() {
		setVisible(true);
		testGame game = new testGame();
		add(game);

		this.setPreferredSize(new Dimension(testGame.WIDTH, testGame.HEIGHT));

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		game.setVisible(true);
		game.init();

	}

	public static void main(String[] args) {
		new gameDebug();
	}
}
