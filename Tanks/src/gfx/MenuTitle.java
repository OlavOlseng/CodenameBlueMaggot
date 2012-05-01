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

		MenuButton btnReturn = new MenuButton(Labels.RETURN, this, game);
		MenuButton btnNewGame = new MenuButton(Labels.NEW_GAME, this, game);
		MenuButton btnNewOnlineGame = new MenuButton(Labels.NEW_LAN_GAME, this, game);
		MenuButton btnAbout = new MenuButton(Labels.ABOUT, this, game);
		MenuButton btnExit = new MenuButton(Labels.EXIT, this, null);
		MenuButton btnLvls = new MenuButton(Labels.LVL_SELECT, this, game);

		// add buttons for title menu here
		ButtonArr.add(btnReturn);
		ButtonArr.add(btnNewGame);
		ButtonArr.add(btnNewOnlineGame);
		ButtonArr.add(btnLvls);
		ButtonArr.add(btnAbout);
		ButtonArr.add(btnExit);

		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 0, 10, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy++;

		add(new MenuLabel(Labels.LOGO), c);
		c.gridy++;
		for (MenuButton button : ButtonArr) {
			add(button, c);
			c.gridy++;
		}
		validate();
		repaint();
	}
}
