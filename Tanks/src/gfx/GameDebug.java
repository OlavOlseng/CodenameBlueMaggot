package gfx;

import inputhandler.InputHandlerMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import baseGame.gameDebug;
import baseGame.testGame;

public class GameDebug extends JFrame implements Runnable {

	InputHandlerMenu input = new InputHandlerMenu(this);
	private JLayeredPane layeredPane = new JLayeredPane();
	public MenuTitle menuTitle = new MenuTitle();
	gameDebug game;

	public GameDebug() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(testGame.WIDTH, testGame.HEIGHT));
		setLayout(null);

		setFocusable(true);
		addKeyListener(input);

		JPanel gamePanel = new JPanel();

		game = new gameDebug();
		gamePanel.setBounds(0, 0, testGame.WIDTH, testGame.HEIGHT);

		gamePanel.add(game);

		// need to override default layoutmanager on jpanel in order to not get
		// a fucking border at top (flowlayout)
		gamePanel.setLayout(new BorderLayout());

		layeredPane.setBounds(0, 0, testGame.WIDTH, testGame.HEIGHT);
		layeredPane.setOpaque(false);

		layeredPane.add(gamePanel, new Integer(0));
		layeredPane.add(menuTitle, new Integer(1));

		add(layeredPane);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void run() {
		game.run();
		repaint();
	}

	public void toggleMenu() {
		if (menuTitle.isVisible()) {
			testGame.PAUSED = false;
			menuTitle.setVisible(false);
		} else {
			testGame.PAUSED = true;
			menuTitle.setVisible(true);
		}
		repaint();
	}

	public static void main(String[] args) {
		Thread t = new Thread(new GameDebug());
		t.start();
	}
}
