package gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import blueMaggot.Game;
import blueMaggot.GameState;

public class UIElement extends Panel {

	int x, y, width, height, player;
	private int border = 5;
	private ArrayList<BufferedImage> wepArr = new ArrayList<BufferedImage>();
	Game game;
	public int scoreOldOne;
	public int scoreOldTwo;

	public UIElement(int x, int y, int width, int height, int border, Game game) {
		this.border = border;
		this.width = width;
		this.height = height;
		this.y = y;
		this.x = x;
		this.game = game;
		setBounds(GameState.getInstance().width / 2 - width / 2, 0, width, height);
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
		g.drawString(GameState.getInstance().nickPlayerOne + ": " + getScore(0), 10, 20);
		g.drawString(GameState.getInstance().nickPlayerTwo + ": " + getScore(1), width / 2, 20);
		scoreOldOne = getScore(0);
		scoreOldTwo = getScore(1);

	}

	public int getScore(int player) {
		switch (player) {
		case 0:
			return GameState.getInstance().playerOneScore;
		case 1:
			return GameState.getInstance().playerTwoScore;
		case 2:
			return GameState.getInstance().playerTreeScore;
		case 3:
			return GameState.getInstance().playerFourScore;
		default:
			return 0;
		}
	}
}
