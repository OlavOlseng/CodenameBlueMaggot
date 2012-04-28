package blueMaggot;

import gfx.MenuLevelSelect;
import gfx.MenuOptions;
import gfx.MenuTitle;
import gfx.UIElement;
import gfx.UIScoreBoard;
import inputhandler.InputHandler;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * @author Habitats * this motherfucker starts the game
 */
public class BlueMaggot extends JFrame implements Runnable {

	public InputHandler inputReal = new InputHandler();

	private JLayeredPane layeredPane = new JLayeredPane();
	private MenuTitle menuTitle;
	private UIScoreBoard uiScoreBoard;
	private JPanel gamePanel;
	public MenuLevelSelect menuLevelSelect;
	public MenuOptions menuOptions;

	private UIElement ui;

	Game game;

	private UIElement ui2;

	private MenuBackground menuBackground;

	public BlueMaggot() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
		setFocusable(true);

		layeredPane.setBounds(0, 0, Game.WIDTH, Game.HEIGHT);
		layeredPane.setOpaque(false);

		game = new blueMaggot.Game(this);
		menuTitle = new MenuTitle(game, this);
		menuOptions = new MenuOptions(game);
		uiScoreBoard = new UIScoreBoard(game);
		menuLevelSelect = new MenuLevelSelect(game);
		menuBackground = new MenuBackground(menuTitle);
		gamePanel = new JPanel();

		ui2 = new UIElement(0, 0, 100, 40, 2);
		// ui2 = new UIElement(Game.WIDTH-100,0,100,40,2);

		layeredPane.add(gamePanel, new Integer(0));
		// layeredPane.add(ui, new Integer(1));
		// layeredPane.add(ui2, new Integer(1));
		layeredPane.add(menuBackground, new Integer(9));
		layeredPane.add(menuTitle, new Integer(10));
		layeredPane.add(menuOptions, new Integer(11));
		layeredPane.add(menuLevelSelect, new Integer(11));
		layeredPane.add(uiScoreBoard, new Integer(2));

		add(layeredPane);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		repaint();
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
				menuBackground.setVisible(true);
				menuTitle.repaint();
				Game.PAUSED = true;
			}
		}
		if (inputReal.tab.down) {
			uiScoreBoard.setVisible(true);
		} else
			uiScoreBoard.setVisible(false);
		if(game.gameOver()){
			uiScoreBoard.setVisible(true);
		}
	}
}
