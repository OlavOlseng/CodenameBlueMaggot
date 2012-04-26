package blueMaggot;

import gfx.MenuTitle;
import inputhandler.InputHandler;
import inputhandler.InputHandlerMenu;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


/**
 * @author Habitats * this motherfucker starts the game
 */
public class BlueMaggot extends JFrame implements Runnable {

	InputHandlerMenu input = new InputHandlerMenu(this);
	public InputHandler inputReal = new InputHandler();
	private JLayeredPane layeredPane = new JLayeredPane();
	public MenuTitle menuTitle;
	JPanel gamePanel = new JPanel();
	Game game;

	public BlueMaggot() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
		setFocusable(true);

		layeredPane.setBounds(0, 0, Game.WIDTH, Game.HEIGHT);
		layeredPane.setOpaque(false);

		game = new blueMaggot.Game(this);
		menuTitle = new MenuTitle(game);
		layeredPane.add(gamePanel, new Integer(0));
		layeredPane.add(menuTitle, new Integer(100));

		add(layeredPane);
		pack();
		repaint();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void setUpGame() {
		game.setPreferredSize(Game.DIMENSION);
		gamePanel.setLayout(new BorderLayout());
		gamePanel.setBounds(0, 0, Game.WIDTH, Game.HEIGHT);
		gamePanel.add(game);
	}

	private void startGame() {
		game.setVisible(true);
		game.startReuglarGame();
	}

	@Override
	public void run() {

		setUpGame();
		startGame();
		validate();
		repaint();
		game.requestFocus();
	}

	public static void main(String[] args) {
		(new BlueMaggot()).run();
	}

	public void tick() {
		// game.requestFocus();
		if (inputReal.menu.clicked) {
			inputReal.menu.clicked = false;
			inputReal.releaseAll();
			if (!menuTitle.isVisible()) {
				menuTitle.setVisible(true);
				Game.PAUSED = true;
				System.out.println("dicks");
			}
		}
	}
}
