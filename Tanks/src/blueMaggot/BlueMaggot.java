package blueMaggot;

import gfx.MenuTitle;
import inputhandler.InputHandler;
import inputhandler.InputHandlerMenu;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import baseGame.BaseGame;
import baseGame.testGame;

/**
 * @author Habitats * this motherfucker starts the game
 */
public class BlueMaggot extends JFrame implements Runnable {

	InputHandlerMenu input = new InputHandlerMenu(this);
	public InputHandler inputReal = new InputHandler();
	private JLayeredPane layeredPane = new JLayeredPane();
	public MenuTitle menuTitle = new MenuTitle();
	JPanel gamePanel = new JPanel();
	testGame game;

	public BlueMaggot() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(testGame.WIDTH, testGame.HEIGHT));
		setFocusable(true);

		// this is temp, goto add read listener or some shit
		// addKeyListener(input);
		addKeyListener(inputReal);

		layeredPane.setBounds(0, 0, testGame.WIDTH, testGame.HEIGHT);
		layeredPane.setOpaque(false);

		layeredPane.add(gamePanel, new Integer(0));
		layeredPane.add(menuTitle, new Integer(1));

		setVisible(true);
		add(layeredPane);
		pack();
		setLocationRelativeTo(null);
	}

	public void setUpGame() {
		game = new baseGame.testGame(this);
		game.setPreferredSize(testGame.DIMENSION);
		gamePanel.setLayout(new BorderLayout());
		gamePanel.setBounds(0, 0, testGame.WIDTH, testGame.HEIGHT);
		gamePanel.add(game);
		System.out.println("Completed: setUpGame");
	}

	public void startGame() {
		game.setVisible(true);
		game.init();
	}

	@Override
	public void run() {
		setUpGame();
		startGame();
	}

	public void toggleMenu() {
		if (menuTitle.isVisible()) {
			testGame.PAUSED = false;
			menuTitle.setVisible(false);
		} else {
			testGame.PAUSED = true;
			menuTitle.setVisible(true);
		}
	}

	public static void main(String[] args) {
		Thread t = new Thread(new BlueMaggot());
		t.start();
	}

	public void tick() {
		if (inputReal.menu.clicked) {
			// inputReal.menu.clicked = false;
			// inputReal.releaseAll();
			if (!menuTitle.isVisible())
				menuTitle.setVisible(true);
			else
				menuTitle.setVisible(false);
			System.out.println("dicks");
		}
	}
}
