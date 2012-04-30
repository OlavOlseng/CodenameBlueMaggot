package gfx;

import java.awt.Graphics;

import blueMaggot.Game;

public class MenuScoreBoard extends Menu {

	public MenuScoreBoard(Game game) {
		setVisible(false);
		add(new MenuButton(Labels.RETURN, this, game),new GBC(0, 0, null).setInsets(5, 5, 5, 5).setIpad(150, 0));
		add(new MenuButton(Labels.RETURN, this, game),new GBC(0, 1, null).setInsets(5, 5, 5, 5));
		add(new MenuButton(Labels.RETURN, this, game),new GBC(0, 2, null).setInsets(5, 5, 5, 5));
	}

	@Override
	public void paint(Graphics g) {
	}

}
