package gfx;

import java.awt.Color;
import java.awt.Dimension;

import blueMaggot.Game;

public class MenuTitle extends Menu {

	private int width = 500;
	private int height = 300;
	public MenuButton btnExit;
	
	private MenuButton btnReturn;
	private MenuButton btnNewGame;
	private MenuButton btnNewOnlineGame;
	private MenuButton btnOptions;

	public MenuTitle(Game game) {
		super();

		super.putCenter(this, width, height);
		super.border = 5;
		super.menuBg = new Color(153, 210, 228);
		
		btnReturn = new MenuButton("Return!", "return", this, game);
		btnNewGame = new MenuButton("New Game!", "newGame", this, game);
		btnNewOnlineGame = new MenuButton("New Online Game!", "newLanGame", this, game);
		btnOptions = new MenuButton("Conn False!", "options", this, game);
		btnExit = new MenuButton("Exit!", "exit", this, null);

		// add buttons for title menu here
		super.ButtonArr.add(btnReturn);
		super.ButtonArr.add(btnNewGame);
		super.ButtonArr.add(btnNewOnlineGame);
		super.ButtonArr.add(btnOptions);
		super.ButtonArr.add(btnExit);

		super.setUpLayout();
	}
}
