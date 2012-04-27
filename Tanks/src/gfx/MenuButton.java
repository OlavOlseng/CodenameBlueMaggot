package gfx;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import blueMaggot.Game;

public class MenuButton extends Button {

	Font f;
	public static final int UNARMED = 0;
	public static final int ARMED = 1;
	public static final int OVER = 2;
	public static final int DISABLED = 3;

	private int buttonState = UNARMED;

	private BufferedImage bgImage;

	private HashMap<String, BufferedImage> buttonMap = new HashMap<String, BufferedImage>();

	public MenuButton(final String label, final String action, final MenuTitle menuTitle, final Game game) {
		File img = new File("./res/titleMenu/" + action + ".png");
		System.out.println(img.toString());
		System.out.println((new File(".")).toString());

		try {
			// bgImage =
			// ImageIO.read(getClass().getResourceAsStream(img.toString()));
			bgImage = ImageIO.read(img);
		} catch (IOException e) {
			e.printStackTrace();
		}

		getFont();

		addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println("Clicked: " + label);
				if (action.equals("exit"))
					System.exit(1);
				else if (action.equals("return")) {
					Game.PAUSED = false;
					menuTitle.setVisible(false);
					game.requestFocus();
				} else if (action.equals("newGame")) {
					try {
						game.runLoop.stop();
					} catch (Exception e) {
					}
					menuTitle.setVisible(false);
					game.startReuglarGame();
					game.requestFocus();
				} else if (action.equals("newOnlineGame")) {
					try {
						game.runLoop.stop();
					} catch (Exception e) {
					}
					menuTitle.setVisible(false);
					game.startOnlineGame();
					game.requestFocus();
				} else if (action.equals("connFalse"))
					game.initConnection(false);
				else if (action.equals("connTrue"))
					game.initConnection(false);
			}
		});

		System.out.println(bgImage.getHeight() + " " + bgImage.getWidth());
	}

	// paint images maybe
	public void paint(Graphics g) {
		super.paint(g);
		setForeground(new Color(255, 212, 218));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(bgImage, (getWidth() - bgImage.getWidth()) / 2, (getHeight() - bgImage.getHeight()) / 2, null);

		System.out.println(getLocation());
	}

	public void getFonts() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.getAllFonts();

		f = new Font("Calibri", Font.BOLD, 10);
	}
}
