package blueMaggot;

import entity.Tank;
import gfx.MenuBackground;
import gfx.MenuLevelSelect;
import gfx.MenuOptions;
import gfx.MenuTitle;
import gfx.ResourceManager;
import gfx.UIElement;
import gfx.MenuScoreBoard;
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
	private JPanel gamePanel;

	public MenuScoreBoard uiScoreBoard;
	public MenuLevelSelect menuLevelSelect;
	public MenuOptions menuOptions;
	public UIElement ui;

	Game game;

	private MenuBackground menuBackground;

	public BlueMaggot() {
		long time = System.currentTimeMillis();
		ResourceManager.getInstance().initResources();
		System.out.println(System.currentTimeMillis()-time);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(GameState.getInstance().width, GameState.getInstance().height));
		setFocusable(true);
		setResizable(false);

		layeredPane.setBounds(0, 0, GameState.getInstance().width, GameState.getInstance().height);
		layeredPane.setOpaque(false);

		game = new blueMaggot.Game(this);
		menuTitle = new MenuTitle(game, this);
		menuOptions = new MenuOptions(game);
		uiScoreBoard = new MenuScoreBoard(game);
		menuLevelSelect = new MenuLevelSelect(game);
		menuBackground = new MenuBackground(menuTitle);
		gamePanel = new JPanel();

		ui = new UIElement(0, 0, 700, 45, menuTitle.border, game);

		layeredPane.add(gamePanel, new Integer(20));
		layeredPane.add(ui, new Integer(1));
		layeredPane.add(menuBackground, new Integer(9));
		layeredPane.add(menuTitle, new Integer(10));
		layeredPane.add(menuOptions, new Integer(11));
		layeredPane.add(menuLevelSelect, new Integer(11));
		layeredPane.add(uiScoreBoard, new Integer(12));

		add(layeredPane);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		repaint();
	}

	private void setUpGame() {
		game.setPreferredSize(GameState.getInstance().dimension);
		
		gamePanel.setLayout(new BorderLayout());
		gamePanel.setBounds(0, 0, GameState.getInstance().width, GameState.getInstance().height);
		
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
		for (Tank tank : GameState.getInstance().players) {
			if (tank.getNick() == null)
				tank.setNick("Player");
		}

		if (inputReal.menu.clicked) {
			inputReal.menu.clicked = false;
			inputReal.releaseAll();
			if (!menuTitle.isVisible()) {
				menuTitle.setVisible(true);
				menuBackground.setVisible(true);
				menuTitle.repaint();
				GameState.getInstance().setPaused(true);
			}
		}
		if (GameState.getInstance().isRunning()) {
			ui.setVisible(true);
		} else
			ui.setVisible(false);
		// TODO: Implement scoreboard
		// if (inputReal.tab.down) {
		// uiScoreBoard.setVisible(true);
		// } else
		// uiScoreBoard.setVisible(false);
		if (GameState.getInstance().isGameOver()) {
			menuTitle.setVisible(true);
			uiScoreBoard.setVisible(true);
			GameState.getInstance().setPaused(true);
			GameState.getInstance().setRunning(false);
			menuBackground.setVisible(true);
			menuTitle.repaint();
		}
		for (Tank tank : GameState.getInstance().players) {
			if (tank.getScore() != tank.getOldScore()) {
				tank.setOldScore(tank.getScore());
				ui.repaint();
				System.out.println("p" + tank.getId() + ": " + tank.getScore());
			}
		}
		if (inputReal.down1.clicked || inputReal.down2.clicked) {
			ui.repaint();
		}
	}
}
