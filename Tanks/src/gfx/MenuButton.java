package gfx;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import entity.Tank;

import blueMaggot.Game;
import blueMaggot.GameState;

public class MenuButton extends Button {

	private BufferedImage bgImage;

	private Game game;
	private Labels label;

	private Menu menu;
	private MenuButton menuButton;
	private Color btnColor = Menu.pink;

	public MenuButton(Labels label, Menu menu, Game game, Dimension size) {
		menuButton = this;

		this.label = label;
		this.menu = menu;
		this.game = game;
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		setUp();
	}

	public MenuButton(Labels label, Menu menu, Game game) {
		this.label = label;
		this.menu = menu;
		this.game = game;
		setUp();
	}

	public void setUp() {
		String img = "/titleMenu/" + label.toString() + ".png";
		try {
			System.out.println("loading: " + img.toString());
			bgImage = ImageIO.read(getClass().getResourceAsStream(img.toString()));
		} catch (IOException e) {
			System.out.println("Nope: " + img.toString());
			e.printStackTrace();
		}

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				btnColor = Menu.pinkDark;
				if (label == Labels.EXIT)
					System.exit(1);
				else if (label == Labels.RETURN) {
					if (menu instanceof MenuScoreBoard) {
						GameState.getInstance().setGameOver(false);
						game.blueMaggot.menuTitle.setVisible(true);
					}
					if (menu instanceof MenuTitle) {
						if (!GameState.getInstance().isRunning())
							return;
						GameState.getInstance().setPaused(false);
						menu.setVisible(false);
						game.requestFocus();
					}
					menu.setVisible(false);
					menu.repaint();
				} else if (label == Labels.NEW_GAME)
					game.blueMaggot.menuOptions.setVisible(true);
				else if (label == Labels.NEW_LAN_GAME)
					game.blueMaggot.menuOptionsLan.setVisible(true);
				else if (label == Labels.START_LAN_GAME || label == Labels.START_GAME) {
					for (Tank tank : GameState.getInstance().getPlayers()) {
						if (tank.getNick() == null)
							return;
						tank.setScore(0);
					}
					if (game.runLoop != null) {
						game.runLoop.stop();
					}
					if (label == Labels.START_LAN_GAME) {
						game.blueMaggot.menuOptionsLan.apply(game);
						game.initConnection(GameState.getInstance().isHost(), GameState.getInstance().hostIp);
						// game.blueMaggot.inputReal.setLan();
					} else if (label == Labels.START_GAME) {
						game.startReuglarGame();
						game.blueMaggot.menuOptions.apply(game);
						GameState.getInstance().setRunning(true);
					}
					GameState.getInstance().setPaused(false);
					menu.setVisible(false);
					game.blueMaggot.menuTitle.setVisible(false);
					menu.repaint();
					game.requestFocus();
					System.out.println("starting game");

				} else if (label == Labels.OPTIONS)
					game.blueMaggot.menuOptions.setVisible(true);
				else if (label == Labels.APPLY) {
					if (menu instanceof MenuLevelSelect)
						game.blueMaggot.menuLevelSelect.apply();
					else if (menu instanceof MenuOptions)
						game.blueMaggot.menuOptions.apply(game);
					menu.setVisible(false);
					menu.repaint();
				} else if (label == Labels.LVL_SELECT)
					game.blueMaggot.menuLevelSelect.setVisible(true);
				else if (label == Labels.ABOUT)
					game.blueMaggot.menuAbout.setVisible(true);
				else if (label == Labels.KEYS)
					game.blueMaggot.menuKeys.setVisible(true);
				else if (label == Labels.DEFAULT) {
					game.blueMaggot.inputReal.resetSingle();
					for (MenuField menuField : MenuField.menuFields)
						menuField.reset();
				} else if (label == Labels.DEFAULT_LAN) {
					game.blueMaggot.inputReal.resetLan();
					for (MenuField menuField : MenuField.menuFields)
						menuField.reset();
				}
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				btnColor = Menu.green;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnColor = Menu.pink;
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnColor = Menu.pinkDark;
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
	}

	// paint images maybe
	@Override
	public void paint(Graphics g) {
		setForeground(btnColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(bgImage, (getWidth() - bgImage.getWidth()) / 2, (getHeight() - bgImage.getHeight()) / 2, null);
	}
}
