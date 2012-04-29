package gfx;

import java.awt.Dimension;
import blueMaggot.Game;
import blueMaggot.GameState;

public class MenuOptions extends Menu {
	private Dimension btnSize = new Dimension(200, 20);

	private MenuField fieldPlayerTwo;
	private MenuField fieldPlayerOne;
	private MenuField fieldIp;

	private MenuButton btnApply;
	private MenuButton btnReturn;

	private MenuLabel playerOne;
	private MenuLabel playerTwo;
	private MenuLabel connectIp;
	private MenuLabel isHost;

	private MenuCheckBox boxIsHost;

	public MenuOptions(Game game) {
		super();

		setVisible(false);

		fieldIp = new MenuField(20, "ip");
		fieldPlayerOne = new MenuField(20, "text");
		fieldPlayerTwo = new MenuField(20, "text");

		btnApply = new MenuButton("apply", this, game, btnSize);
		btnReturn = new MenuButton("return", this, game, btnSize);

		playerOne = new MenuLabel("playerOne");
		playerTwo = new MenuLabel("playerTwo");
		connectIp = new MenuLabel("connectIp");
		isHost = new MenuLabel("isHost");

		boxIsHost = new MenuCheckBox();

		// left column
		add(playerOne, new GBC(0, 0, "right"));
		add(playerTwo, new GBC(0, 1, "right"));
		add(connectIp, new GBC(0, 2, "right"));
		add(isHost, new GBC(0, 3, "right"));
		add(boxIsHost, new GBC(1, 3, "left"));

		// right column
		add(fieldPlayerOne, new GBC(1, 0, "left"));
		add(fieldPlayerTwo, new GBC(1, 1, "left"));
		add(fieldIp, new GBC(1, 2, "left"));

		add(btnApply, new GBC(0, 4, "right"));
		add(btnReturn, new GBC(1, 4, "left"));
	}

	public void apply(Game game) {
		GameState.getInstance().isHost = boxIsHost.getState();
		if (fieldPlayerOne.msg != null)
			GameState.getInstance().players.get(0).setNick(fieldPlayerOne.msg);
		if (fieldPlayerTwo.msg != null)
			GameState.getInstance().players.get(1).setNick(fieldPlayerTwo.msg);
		GameState.getInstance().hostIp = fieldIp.msg;
		game.blueMaggot.ui.repaint();
	}
}
