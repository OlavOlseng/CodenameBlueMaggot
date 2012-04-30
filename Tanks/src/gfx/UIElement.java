package gfx;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
	private Font f = new Font("Consolas", Font.BOLD, 20);

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
		for (Tank tank : GameState.getInstance().players) {
		}
		if (GameState.getInstance().isRunning()) {
			String playerOneHearts = "";
			String playerTwoHearts = "";
			playerOne = GameState.getInstance().players.get(0);
			playerTwo = GameState.getInstance().players.get(1);

			for (int i = 0; playerOneHearts.length() < playerOne.getLife(); i++)
				playerOneHearts += "o";
			for (int i = 0; playerTwoHearts.length() < playerTwo.getLife(); i++)
				playerTwoHearts += "o";
			String statsOne = String.format("%s|%s|%s|%s", playerOne.getNick(), playerOneHearts, playerOne.getScore(), playerOne.getCurrentWeaponName());
			String statsTwo = String.format("%s|%s|%s|%s", playerTwo.getCurrentWeaponName(), playerTwo.getScore(), playerTwoHearts, playerTwo.getNick());

			FontMetrics fm = g.getFontMetrics(f);
			System.out.println(fm.stringWidth(statsTwo));

			g.setColor(Menu.pink);
			g.setFont(f);
			g.fillRect(border + border, border, (width / 2 - border * 2) - border, (height - border * 2) - border);
			g.fillRect(width / 2 + border, border, (width / 2 - border * 2) - border, (height - border * 2) - border);
			g.setColor(Color.black);
			System.out.println(fm.stringWidth(statsTwo));
			g.drawString(statsTwo, width - 15 - fm.stringWidth(statsTwo), 26);
			g.drawString(statsOne, 15, 26);

			System.out.println("changing points");
		}
	}
}
