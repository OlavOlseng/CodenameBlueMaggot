package gfx;

import java.awt.Dimension;
import blueMaggot.Game;
import blueMaggot.GameState;

public class MenuOptions extends Menu {
	private Dimension btnSize = new Dimension(200, 20);

	private MenuField fieldPlayerTwo;
	private MenuField fieldPlayerOne;

	private MenuButton btnStart;
	private MenuButton btnReturn;

	private MenuLabel playerOne;
	private MenuLabel playerTwo;

	private MenuCheckBox boxIsHost;

	public MenuOptions(Game game) {
		super();

		setVisible(false);

		fieldPlayerOne = new MenuField(20, "text");
		fieldPlayerTwo = new MenuField(20, "text");

		btnStart = new MenuButton("startGame", this, game, btnSize);
		btnReturn = new MenuButton("return", this, game, btnSize);

		playerOne = new MenuLabel("playerOne");
		playerTwo = new MenuLabel("playerTwo");

		boxIsHost = new MenuCheckBox();

		// left column
		add(playerOne, new GBC(0, 0, "right"));
		add(playerTwo, new GBC(0, 1, "right"));

		// right column
		add(fieldPlayerOne, new GBC(1, 0, "left"));
		add(fieldPlayerTwo, new GBC(1, 1, "left"));

		add(btnStart, new GBC(0, 4, "right"));
		add(btnReturn, new GBC(1, 4, "left"));
	}

	public void apply(Game game) {
		if (fieldPlayerOne.msg != null)
			GameState.getInstance().players.get(0).setNick(fieldPlayerOne.msg);
		if (fieldPlayerTwo.msg != null)
			GameState.getInstance().players.get(1).setNick(fieldPlayerTwo.msg);
		game.blueMaggot.ui.repaint();
	}
}
