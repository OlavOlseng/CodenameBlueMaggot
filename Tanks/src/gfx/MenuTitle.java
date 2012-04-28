package gfx;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import blueMaggot.BlueMaggot;
import blueMaggot.Game;

public class MenuTitle extends Menu {
	private ArrayList<MenuButton> ButtonArr = new ArrayList<MenuButton>();
	private GridBagConstraints c = new GridBagConstraints();

	public MenuTitle(Game game, BlueMaggot blueMaggot) {
		super();

		MenuButton btnReturn = new MenuButton("return", this, game);
		MenuButton btnNewGame = new MenuButton("newGame", this, game);
		MenuButton btnNewOnlineGame = new MenuButton("newLanGame", this, game);
		MenuButton btnOptions = new MenuButton("options", this, game);
		MenuButton btnExit = new MenuButton("exit", this, null);
		MenuButton btnLvls = new MenuButton("lvls", this, game);

		// add buttons for title menu here
		ButtonArr.add(btnReturn);
		ButtonArr.add(btnNewGame);
		ButtonArr.add(btnNewOnlineGame);
		ButtonArr.add(btnLvls);
		ButtonArr.add(btnOptions);
		ButtonArr.add(btnExit);

		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 5;
		c.ipadx = 200;
		c.insets = new Insets(10, 0, 10, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy++;

		for (MenuButton button : ButtonArr) {
			add(button, c);
			c.gridy++;
			System.out.println("adding button");
		}
		validate();
		repaint();
	}
}
