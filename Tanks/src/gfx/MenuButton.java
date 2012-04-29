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
	private String label;

	private Menu menu;
	private MenuButton menuButton;
	private Color btnColor = Menu.pink;

	public MenuButton(String label, Menu menu, Game game, Dimension size) {
		menuButton = this;

		this.label = label;
		this.menu = menu;
		this.game = game;
		setUp();
	}

	public MenuButton(String label, Menu menu, Game game) {
		this.label = label;
		this.menu = menu;
		this.game = game;
		setUp();
	}

	public void setUp() {
		String img = "/titleMenu/" + label + ".png";
		try {
			System.out.println(img.toString());
			bgImage = ImageIO.read(getClass().getResourceAsStream(img.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				btnColor = Menu.pinkDark;
				if (label.equals("exit"))
					System.exit(1);
				else if (label.equals("return")) {
					if (!GameState.getInstance().isRunning() && !game.blueMaggot.menuOptions.isVisible() && !game.blueMaggot.menuLevelSelect.isVisible() && !GameState.getInstance().isGameOver())
						return;
					if (!game.blueMaggot.menuOptions.isVisible() && !game.blueMaggot.menuLevelSelect.isVisible() && !game.blueMaggot.uiScoreBoard.isVisible())
						GameState.getInstance().setPaused(false);
					menu.setVisible(false);
					if (menu instanceof MenuScoreBoard) {
						GameState.getInstance().setGameOver(false);
					}
					menu.repaint();
					game.requestFocus();
				} else if (label.equals("newGame")) {
					for (Tank tank : GameState.getInstance().players) {
						if (tank.getNick() == null)
							return;
						tank.setScore(0);
					}
					try {
						game.runLoop.stop();
					} catch (Exception e) {
						 e.printStackTrace();
					}
					GameState.getInstance().setPaused(false);
					menu.setVisible(false);
					menu.repaint();
					game.startReuglarGame();
					game.requestFocus();
				} else if (label.equals("newLanGame")) {
					for (Tank tank : GameState.getInstance().players) {
						if (tank.getNick() == null)
							return;
						tank.setScore(0);
					}
					try {
						game.runLoop.stop();
					} catch (Exception e) {
						e.printStackTrace();
					}
					GameState.getInstance().setPaused(false);
					menu.setVisible(false);
					menu.repaint();
					game.initConnection(GameState.getInstance().isHost, GameState.getInstance().hostIp);
					game.requestFocus();
					
				} else if (label.equals("options"))
					game.blueMaggot.menuOptions.setVisible(true);
				else if (label.equals("apply")) {
					if (menu instanceof MenuLevelSelect)
						game.blueMaggot.menuLevelSelect.apply();
					else if (menu instanceof MenuOptions)
						game.blueMaggot.menuOptions.apply(game);
					menu.setVisible(false);
					menu.repaint();
				} else if (label.endsWith("lvls"))
					game.blueMaggot.menuLevelSelect.setVisible(true);

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
