package blueMaggot;

import gfx.Menu;
import gfx.MenuOptions;
import gfx.MenuTitle;
import inputhandler.InputHandler;
import inputhandler.InputHandlerMenu;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * @author Habitats * this motherfucker starts the game
 */
public class BlueMaggot extends JFrame implements Runnable {

//	InputHandlerMenu input = new InputHandlerMenu(this);
	public InputHandler inputReal = new InputHandler();
	
	private JLayeredPane layeredPane = new JLayeredPane();
	private MenuTitle menuTitle;
	public MenuOptions menuOptions;
	
	JPanel gamePanel = new JPanel();
	Game game;

	public BlueMaggot() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
		setFocusable(true);

		layeredPane.setBounds(0, 0, Game.WIDTH, Game.HEIGHT);
		layeredPane.setOpaque(false);

		game = new blueMaggot.Game(this);
		menuTitle = new MenuTitle(game,this);
		menuOptions = new MenuOptions(game);
		
		layeredPane.add(gamePanel, new Integer(0));
//		layeredPane.add(ui, new Integer(1));
		layeredPane.add(menuTitle, new Integer(10));
		layeredPane.add(menuOptions, new Integer(11));

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

	@Override
	public void run() {
		setUpGame();
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
			}
		}
	}
}
