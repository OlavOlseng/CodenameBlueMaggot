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
				System.out.println("Clicked: " + label);
				if (label.equals("exit"))
					System.exit(1);
				else if (label.equals("return")) {
					if (!GameState.getInstance().running && !game.blueMaggot.menuOptions.isVisible() && !game.blueMaggot.menuLevelSelect.isVisible())
						return;
					if (!game.blueMaggot.menuOptions.isVisible() && !game.blueMaggot.menuLevelSelect.isVisible())
						GameState.getInstance().PAUSED = false;
					menu.setVisible(false);
					menu.repaint();
					game.requestFocus();
				} else if (label.equals("newGame")) {
					if (GameState.getInstance().nickPlayerOne == null || GameState.getInstance().nickPlayerTwo == null)
						return;
					try {
						game.runLoop.stop();
					} catch (Exception e) {
						// e.printStackTrace();
					}
					GameState.getInstance().PAUSED = false;
					menu.setVisible(false);
					menu.repaint();
					game.startReuglarGame();
					game.requestFocus();
				} else if (label.equals("newOnlineGame")) {
					try {
						game.runLoop.stop();
					} catch (Exception e) {
					}
					menu.setVisible(false);
					menu.repaint();
					game.startOnlineGame();
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

				System.out.println("Player One: " + GameState.getInstance().nickPlayerOne + " - Player Two: " + GameState.getInstance().nickPlayerTwo + " - Is Host: " + GameState.getInstance().isHost
						+ " - Host IP: " + GameState.getInstance().hostIp);
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
