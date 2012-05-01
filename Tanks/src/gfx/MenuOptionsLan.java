package gfx;

import gfx.GBC.Align;
import gfx.MenuField.FieldType;

import java.awt.Dimension;
import java.util.Comparator;

import blueMaggot.Game;
import blueMaggot.GameState;

public class MenuOptionsLan extends Menu{
	private Dimension btnSize = new Dimension(200, 20);

	private MenuField fieldPlayerTwo;
	private MenuField fieldLanNick;
	private MenuField fieldIp;

	private MenuButton btnStartLan;
	private MenuButton btnReturn;

	private MenuLabel playerOne;
	private MenuLabel playerTwo;
	private MenuLabel connectIp;
	private MenuLabel isHost;

	private MenuCheckBox boxIsHost;

	public MenuOptionsLan(Game game) {
		super();

		setVisible(false);

		fieldIp = new MenuField(20, FieldType.IP);
		fieldLanNick = new MenuField(20,FieldType.TEXT);
		fieldPlayerTwo = new MenuField(20, FieldType.TEXT);

		btnStartLan = new MenuButton(Labels.START_LAN_GAME, this, game, btnSize);
		btnReturn = new MenuButton(Labels.RETURN, this, game, btnSize);

		playerOne = new MenuLabel(Labels.LAN_NICK);
		connectIp = new MenuLabel(Labels.CONNECT_IP);
		isHost = new MenuLabel(Labels.IS_HOST);

		boxIsHost = new MenuCheckBox();

		// left column
		add(playerOne, new GBC(0, 0, Align.RIGHT));
		add(connectIp, new GBC(0, 2, Align.RIGHT));
		add(isHost, new GBC(0, 3, Align.RIGHT));
		add(boxIsHost, new GBC(1, 3, Align.RIGHT));

		// right column
		add(fieldLanNick, new GBC(1, 0, Align.LEFT));
		add(fieldIp, new GBC(1, 2, Align.LEFT));
		add(btnStartLan, new GBC(0, 4, Align.RIGHT));
		add(btnReturn, new GBC(1, 4, Align.LEFT));
	
	}

	public void apply(Game game) {
		GameState.getInstance().setHost(boxIsHost.getState());
		 if(fieldLanNick.msg != null){
			if (GameState.getInstance().isHost())
				GameState.getInstance().setPlayer2Nick(fieldLanNick.msg);
			else
				GameState.getInstance().setPlayer1Nick(fieldLanNick.msg);
		 }
		GameState.getInstance().hostIp = fieldIp.msg;
		
		System.out.println(GameState.getInstance().getPlayer2Nick());
		// game.blueMaggot.ui.repaint();
	}

	
}
