package gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import blueMaggot.Game;

public class UIElement extends Panel {

	int x, y, width, height, player;
	private int border = 5;
	private ArrayList<BufferedImage> wepArr = new ArrayList<BufferedImage>();
	Game game;

	public UIElement(int x, int y, int width, int height, int border, Game game) {
		this.border = border;
		this.width = width;
		this.height = height;
		this.y = y;
		this.x = x;
		this.game = game;
		setBounds(Game.WIDTH / 2 - width / 2, 0, width, height);
		System.out.println("Creating ui element");
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Menu.blue);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		g.fillRect(width - border, 0, border, height);
		g.fillRect(0, 0, border, height);
		g.fillRect(0, height - border, width, border);
		// g.drawImage(selectedWep, 0, 0, null);
		try {
			g.drawString(game.nickPlayerOne + ": " + Integer.toString(game.level.getPlayers().get(0).getScore()), 10, 20);
			g.drawString(game.nickPlayerTwo + ": " + Integer.toString(game.level.getPlayers().get(1).getScore()), width / 2, 20);
		} catch (Exception e) {
			System.out.println("score null");
		}
	}
}
