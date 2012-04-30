package gfx;

import gfx.GBC.Align;
import gfx.Labels;

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

		btnStart = new MenuButton(Labels.START_GAME, this, game, btnSize);
		btnReturn = new MenuButton(Labels.RETURN, this, game, btnSize);

		playerOne = new MenuLabel(Labels.PLAYER_ONE);
		playerTwo = new MenuLabel(Labels.PLAYER_TWO);

		boxIsHost = new MenuCheckBox();

		// left column
		add(playerOne, new GBC(0, 0,Align.RIGHT ));
		add(playerTwo, new GBC(0, 1, Align.RIGHT ));

		// right column
		add(fieldPlayerOne, new GBC(1, 0, Align.LEFT));
		add(fieldPlayerTwo, new GBC(1, 1, Align.LEFT));

		add(btnStart, new GBC(0, 4, Align.RIGHT ));
		add(btnReturn, new GBC(1, 4, Align.LEFT));
	}

	public void apply(Game game) {
		if (fieldPlayerOne.msg != null)
			GameState.getInstance().getPlayers().get(0).setNick(fieldPlayerOne.msg);
		if (fieldPlayerTwo.msg != null)
			GameState.getInstance().getPlayers().get(1).setNick(fieldPlayerTwo.msg);
//		game.blueMaggot.ui.repaint();
	}
}
