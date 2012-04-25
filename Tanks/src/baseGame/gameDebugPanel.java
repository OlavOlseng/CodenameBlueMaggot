package baseGame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class gameDebugPanel extends JPanel {
	testGame game;
	JFrame frame = new JFrame();

	public gameDebugPanel() {
		setLayout(new BorderLayout());
		game = new testGame();
		game.setPreferredSize(testGame.DIMENSION);
		setBounds(0, 0, testGame.WIDTH, testGame.HEIGHT);
		add(game);
		setOpaque(false);
		setVisible(true);
	}

	public void run() {
		game.setVisible(true);
		game.init();
		System.out.println("initiated game");
	}
}
