package gfx;

import entity.Tank;
import entity.weapon.Gun;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import blueMaggot.GameState;

public class GameOverlay {

	// private Font font = new Font("Consolas", Font.BOLD, 20);
	private int score1;
	private int score2;
	private int lives1;
	private int lives2;
	private Gun wep1;
	private Gun wep2;

	private String playerOneScore;
	private String playerTwoScore;

	public void paintOverlay(Graphics2D g, int score1, int score2, int lives1, int lives2, Gun wep1, Gun wep2) {

		this.score1 = score1;
		this.score2 = score2;
		this.lives1 = lives1;
		this.lives2 = lives2;
		this.wep1 = wep1;
		this.wep2 = wep2;

		Tank playerOne;
		Tank playerTwo;

		int width = 850;
		int height = 45;
		int border = 4;
		int alignMent = (GameState.getInstance().getWidth() / 2 - (width) / 2);

		g.setColor(Menu.blue);
		g.setFont(Menu.font);

		if (GameState.getInstance().isRunning()) {
			playerOne = GameState.getInstance().getPlayers().get(1);
			playerTwo = GameState.getInstance().getPlayers().get(0);
			playerOneScore = Integer.toString(playerOne.getScore());
			playerTwoScore = Integer.toString(playerTwo.getScore());

			String statsOne = String.format("%s I %s I %s", GameState.getInstance().getPlayer1Nick(), playerOneScore, playerOne.getCurrentWeaponName()); 
			String statsTwo = String.format("%s I %s I %s", playerTwo.getCurrentWeaponName(), playerTwoScore, GameState.getInstance().getPlayer2Nick());

			FontMetrics fm = g.getFontMetrics(Menu.font);

			g.fillRect(alignMent, 0, width, height);
			g.setColor(Color.black);
			g.fillRect(width - border + alignMent, 0, border, height);
			g.fillRect(alignMent, 0, border, height);
			g.fillRect(alignMent, height - border, width, border);

			g.setColor(Menu.pink);
			g.fillRect(border + border + alignMent, border, (width / 2 - border * 2) - border, (height - border * 2) - border);
			g.fillRect(width / 2 + border + alignMent, border, (width / 2 - border * 2) - border, (height - border * 2) - border);

			g.setFont(Menu.font);
			g.setColor(Color.black);
			g.drawString(statsOne, 15 + alignMent, 26);
			g.drawString(statsTwo, width - fm.stringWidth(statsTwo) - 15 + alignMent, 26);
			for (int i = 0; i < playerOne.getLife(); i++) {
				g.drawImage(ResourceManager.getInstance().HEART.getRgbBufferedImage(), GameState.getInstance().getWidth() / 2 - 20 - i * 14, 15, null);
			}
			for (int i = 0; i < playerTwo.getLife(); i++) {
				g.drawImage(ResourceManager.getInstance().HEART.getRgbBufferedImage(), GameState.getInstance().getWidth() / 2 + 7 + i * 14, 15, null);
			}
		}
	}

	public boolean needUppdate(int score1, int score2, int lives1, int lives2, Gun wep1, Gun wep2) {
		return this.score1 != score1 || this.score2 != score2 || this.lives1 != lives1 || this.lives2 != lives2 || this.wep1 != wep1 || this.wep2 != wep2;
	}
}
