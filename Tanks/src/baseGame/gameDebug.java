package baseGame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Habitats
 * run gfx.GameDebug instead of this one!
 */
public class gameDebug extends JPanel {
	testGame game;
	JFrame frame = new JFrame();

	public gameDebug() {
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
