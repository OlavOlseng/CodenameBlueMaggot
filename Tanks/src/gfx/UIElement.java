package gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Tank;
import blueMaggot.Game;
import blueMaggot.GameState;

public class UIElement extends Panel {

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
		if (GameState.getInstance().running) {
			playerOne = GameState.getInstance().players.get(0);
			playerTwo = GameState.getInstance().players.get(1);
			g.drawString(playerOne.getNick() + ": " + playerOne.getScore(), 10, 20);
			g.drawString(playerTwo.getNick() + ": " + playerTwo.getScore(), width / 2, 20);
		}
	}
}
