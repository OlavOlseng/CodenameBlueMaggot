package gfx;

import gfx.GBC.Align;
import gfx.Labels;

import blueMaggot.Game;

public class MenuAbout extends Menu {

	int x, y;

	public MenuAbout(Game game) {
		setVisible(false);
		add(new MenuLabel(Labels.LOGO), new GBC(x, y, Align.MID).setWeight(1, 0));
		add(new MenuLabel(Labels.CREATORS), new GBC(x, ++y, null).setInsets(5, 5, 5, 5));
		add(new MenuLabel(Labels.CREATORS2), new GBC(x, ++y, null).setInsets(5, 15, 5, 5).setIpad(0, 40));
		add(new MenuButton(Labels.RETURN, this, game), new GBC(x, ++y, null).setInsets(5, 5, 5, 5).setIpad(150, 0));
	}
}
