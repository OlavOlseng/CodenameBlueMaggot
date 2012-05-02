package gfx;

import java.awt.Color;
import java.awt.Graphics;

import entity.Tank;

import blueMaggot.Game;
import blueMaggot.GameState;

public class MenuScoreBoard extends Menu {

	private Tank playerOne;
	private Tank playerTwo;
	private int borderLeft = 40;
	private int borderTop = 80;

	public MenuScoreBoard(Game game) {
		setVisible(false);
		// add(new MenuButton(Labels.RETURN, this, game), new GBC(0, 1,
		// null).setInsets(5, 5, 5, 5));
		add(new MenuButton(Labels.RETURN, this, game), new GBC(0, 0, null).setInsets(200, 5, 5, 5).setIpad(150, 0));
	}

	@Override
	public void paint(Graphics g) {
		playerOne = GameState.getInstance().getPlayers().get(1);
		playerTwo = GameState.getInstance().getPlayers().get(0);

		g.setColor(Color.black);
		g.setFont(Menu.font);
		g.drawString("Scores", borderLeft, borderTop);
		g.drawString(playerOne.getNick() + "  -  POINTS: " + playerOne.getScore() + "  -  LIFE: " + playerOne.getLife(), borderLeft, borderTop + 50);
		g.drawString(playerTwo.getNick() + "  -  POINTS: " + playerTwo.getScore() + "  -  LIFE: " + playerTwo.getLife(), borderLeft, borderTop + 80);
	}
}
