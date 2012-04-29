package gfx;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Tank;
import blueMaggot.Game;
import blueMaggot.GameState;

public class UIElement extends Canvas {

	int x, y, width, height, player;
	private int border = 5;
	private ArrayList<BufferedImage> wepArr = new ArrayList<BufferedImage>();
	Game game;
	public int scoreOldOne;
	public int scoreOldTwo;
	private Tank playerOne;
	private Tank playerTwo;

	public UIElement(int x, int y, int width, int height, int border, Game game) {
		this.border = border;
		this.width = width;
		this.height = height;
		this.y = y;
		this.x = x;
		this.game = game;
		setVisible(false);
		setBounds(GameState.getInstance().width / 2 - width / 2, 0, width, height);
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
		if (GameState.getInstance().isRunning()) {
			playerOne = GameState.getInstance().players.get(0);
			playerTwo = GameState.getInstance().players.get(1);
			g.setFont(new Font("Consolas", Font.BOLD, 20));
			String statsOne = playerOne.getCurrentWeaponName() + "|" + playerOne.getScore() + "|" + playerOne.getNick();
			g.setColor(Menu.pink);
			g.fillRect(border+border, border, (width / 2 - border * 2) - border, (height - border * 2) - border);
			g.fillRect(width / 2 + border, border, (width / 2 - border * 2) - border, (height - border * 2) - border);
			g.setColor(Color.black);
			g.drawString(statsOne, 15, 26);
			String statsTwo = playerTwo.getNick() + "|" + playerTwo.getScore() + "|" + playerTwo.getCurrentWeaponName();
			g.drawString(statsTwo, width - (statsTwo.length() * 11) - 15, 26);
			System.out.println("changing points");
		}
	}
}
