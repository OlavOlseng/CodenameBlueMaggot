package blueMaggot;

import entity.Tank;
import gfx.GBC;
import gfx.GBC.Align;
import gfx.MenuBackground;
import gfx.MenuField;
import gfx.MenuKeys;
import gfx.MenuLevelSelect;
import gfx.MenuOptions;
import gfx.MenuOptionsLan;
import gfx.MenuTitle;
import gfx.MenuScoreBoard;
import gfx.MenuAbout;
import inputhandler.InputHandler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author Habitats * this motherfucker starts the game
 */
public class BlueMaggot extends JFrame implements Runnable,GameListener{

	public InputHandler inputReal = new InputHandler();

	private JLayeredPane layeredPane = new JLayeredPane();
	private JPanel gamePanel;

	public MenuScoreBoard uiScoreBoard;
	public MenuLevelSelect menuLevelSelect;
	public MenuOptions menuOptions;
	public MenuOptionsLan menuOptionsLan;
	public MenuAbout menuAbout;
	public MenuTitle menuTitle;
	public MenuKeys menuKeys;
	public static Exception e;

	Game game;

	private MenuBackground menuBackground;

	public BlueMaggot() {
		GameState.getInstance().init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setPreferredSize(new Dimension(GameState.getInstance().getWidth(), GameState.getInstance().getHeight() + 28));
		setFocusable(true);
		setResizable(false);

		layeredPane.setBounds(0, 0, GameState.getInstance().getWidth(), GameState.getInstance().getHeight());

		layeredPane.setOpaque(false);

		game = new blueMaggot.Game(this);
		

		menuTitle = new MenuTitle(game, this);
		menuOptions = new MenuOptions(game);
		menuOptionsLan = new MenuOptionsLan(game);
		menuAbout = new MenuAbout(game);
		uiScoreBoard = new MenuScoreBoard(game);
		menuLevelSelect = new MenuLevelSelect(game);
		menuBackground = new MenuBackground(menuTitle);
		menuKeys = new MenuKeys(game);
		gamePanel = new JPanel();
		gamePanel.setBackground(Color.DARK_GRAY);

		layeredPane.add(gamePanel, new Integer(0));
		layeredPane.add(menuBackground, new Integer(9));
		layeredPane.add(menuTitle, new Integer(10));
		layeredPane.add(menuOptions, new Integer(11));
		layeredPane.add(menuOptionsLan, new Integer(11));
		layeredPane.add(menuLevelSelect, new Integer(11));
		layeredPane.add(menuAbout, new Integer(11));
		layeredPane.add(menuKeys, new Integer(11));
		
		// reset keybindings to default
		inputReal.resetLan();
		for (MenuField menuField : MenuField.menuFields) {
			menuField.reset();
		}

		add(layeredPane);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		repaint();

	}

	private void setUpGame() {
		game.setPreferredSize(GameState.getInstance().dimension);
		gamePanel.setLayout(new BorderLayout());
		gamePanel.setBounds(0, 0, GameState.getInstance().getWidth(), GameState.getInstance().getHeight());

		gamePanel.add(game);
	}

	@Override
	public void run() {
		setUpGame();
	}

	public static void main(String[] args) {
		try {
			(new BlueMaggot()).run();
		} catch (Exception exception) {
			e = exception;
		} finally {
			if (e != null) {
				JFrame warning = new JFrame();
				JTextArea content = new JTextArea();
				warning.setLayout(new GridBagLayout());
				content.append("FATAL MALVISIOUS ERROR!!11\n\n");
				content.append("Protip:\nMake sure your \"lvl\" directory is in the same folder as your blueMaggot.jar file!\n\n");
				content.append("Error:\n " + e.toString() + "\n\n");
				content.append("StackTrace:\n");
				for (StackTraceElement stack : e.getStackTrace()) {
					content.append(stack.toString() + "\n");
				}
				warning.setTitle("ERROR");
				e.printStackTrace();
				JButton exit = new JButton("exit");
				exit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(1);
					}
				});
				warning.add(content, new GBC(0, 0, Align.MID));
				warning.add(exit, new GBC(0, 1, Align.MID));
				warning.pack();
				warning.setVisible(true);
				warning.setAlwaysOnTop(true);
				warning.setLocationRelativeTo(null);

			}
		}
	}

	public void tick(Graphics2D g) {
		for (Tank tank : GameState.getInstance().getPlayers()) {
			if (tank.getNick() == null)
				tank.setNick("Player");
		}

		if (inputReal.menu.clicked) {
			inputReal.menu.clicked = false;
			inputReal.releaseAll();
			if (!menuTitle.isVisible()) {
				menuTitle.setVisible(true);
				menuBackground.setVisible(true);
				GameState.getInstance().setPaused(true);
			}
		}

		// TODO: Implement scoreboard
		if (GameState.getInstance().isGameOver()) {
			System.out.println("Inmenu");
			menuTitle.setVisible(true);
			uiScoreBoard.setVisible(true);
			GameState.getInstance().setPaused(true);
			GameState.getInstance().setRunning(false);
			menuBackground.setVisible(true);
		}
	}

	@Override
	public void ConnectionFailed(String msg) {
		GameState.getInstance().setPaused(true);
		GameState.getInstance().setRunning(false);
	}
}
