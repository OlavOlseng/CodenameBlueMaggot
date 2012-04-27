package gfx;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import blueMaggot.Game;

public class MenuButton extends Button {

	private BufferedImage bgImage;

	private Game game;
	private String label;

	private Menu menu;

	public MenuButton(String label, Menu menu, Game game, Dimension size) {

		this.label = label;
		this.menu = menu;
		this.game = game;
		// setPreferredSize(size);
		// setSize(size);

		setUp();
	}

	public MenuButton(String label, Menu menu, Game game) {
		this.label = label;
		this.menu = menu;
		this.game = game;

		setUp();
	}

	public void setUp() {
		File img = new File("./res/titleMenu/" + label + ".png");
		try {
			bgImage = ImageIO.read(img);
		} catch (IOException e) {
			e.printStackTrace();
		}

		addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println("Clicked: " + label);
				if (label.equals("exit"))
					System.exit(1);
				else if (label.equals("return")) {
					if (!game.blueMaggot.menuOptions.isVisible())
						Game.PAUSED = false;
					menu.setVisible(false);
					game.requestFocus();
				} else if (label.equals("newGame")) {
					try {
						game.runLoop.stop();
					} catch (Exception e) {
					}
					Game.PAUSED = false;
					menu.setVisible(false);
					game.startReuglarGame();
					game.requestFocus();
				} else if (label.equals("newOnlineGame")) {
					try {
						game.runLoop.stop();
					} catch (Exception e) {
					}
					menu.setVisible(false);
					game.startOnlineGame();
					game.requestFocus();
				} else if (label.equals("options"))
					game.blueMaggot.menuOptions.setVisible(true);
				else if (label.equals("apply"))
					game.blueMaggot.menuOptions.apply(game);

				System.out.println("Player One: " + game.nickPlayerOne + " - Player Two: " + game.nickPlayerTwo + " - Is Host: " + game.isHost + " - Host IP: "
						+ game.hostIp);
				repaint();
			}
		});
	}

	// paint images maybe
	@Override
	public void paint(Graphics g) {
		setForeground(new Color(255, 212, 218));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(bgImage, (getWidth() - bgImage.getWidth()) / 2, (getHeight() - bgImage.getHeight()) / 2, null);
	}
}
