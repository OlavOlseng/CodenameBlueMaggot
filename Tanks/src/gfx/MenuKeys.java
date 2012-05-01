package gfx;

import inputhandler.Action;

import java.awt.Dimension;

import gfx.GBC.Align;
import gfx.MenuField.FieldType;
import blueMaggot.Game;

public class MenuKeys extends Menu {

	Game game;

	public MenuKeys(Game game) {
		setVisible(false);
		this.game = game;
		int x = 0;
		int y = 0;

		add(new MenuLabel(Labels.ONE, new Dimension(40, 13)), new GBC(1, y = 0, Align.LEFT));
		add(new MenuLabel(Labels.LEFT), new GBC(x, ++y, Align.TIGHT));
		add(new MenuLabel(Labels.RIGHT), new GBC(x, ++y, Align.TIGHT));
		add(new MenuLabel(Labels.JETPACK), new GBC(x, ++y, Align.TIGHT));
		add(new MenuLabel(Labels.SHOOT), new GBC(x, ++y, Align.TIGHT));
		add(new MenuLabel(Labels.CYCLE_WEAPON), new GBC(x, ++y, Align.TIGHT));
		add(new MenuLabel(Labels.ANGLE_CLOCKWISE), new GBC(x, ++y, Align.TIGHT));
		add(new MenuLabel(Labels.ANGLE_COUNTERCLOCKWISE), new GBC(x, ++y, Align.TIGHT));

		add(new MenuField(1, FieldType.KEY, Action.LEFT), new GBC(++x, y = 1, Align.TIGHT));
		add(new MenuField(1, FieldType.KEY, Action.RIGHT), new GBC(x, ++y, Align.TIGHT));
		add(new MenuField(1, FieldType.KEY, Action.JETPACK), new GBC(x, ++y, Align.TIGHT));
		add(new MenuField(1, FieldType.KEY, Action.SHOOT), new GBC(x, ++y, Align.TIGHT));
		add(new MenuField(1, FieldType.KEY, Action.CYCLE_WEAPON), new GBC(x, ++y, Align.TIGHT));
		add(new MenuField(1, FieldType.KEY, Action.ANGLE_CLOCKWISE), new GBC(x, ++y, Align.TIGHT));
		add(new MenuField(1, FieldType.KEY, Action.ANGLE_COUNTERCLOCKWISE), new GBC(x, ++y, Align.TIGHT));

		add(new MenuLabel(Labels.TWO, new Dimension(30, 13)), new GBC(2, y = 0, Align.LEFT));
		add(new MenuLabel(Labels.LEFT), new GBC(x, ++y, Align.TIGHT));
		add(new MenuLabel(Labels.RIGHT), new GBC(x, ++y, Align.TIGHT));
		add(new MenuLabel(Labels.JETPACK), new GBC(x, ++y, Align.TIGHT));
		add(new MenuLabel(Labels.SHOOT), new GBC(x, ++y, Align.TIGHT));
		add(new MenuLabel(Labels.CYCLE_WEAPON), new GBC(x, ++y, Align.TIGHT));
		add(new MenuLabel(Labels.ANGLE_CLOCKWISE), new GBC(x, ++y, Align.TIGHT));
		add(new MenuLabel(Labels.ANGLE_COUNTERCLOCKWISE), new GBC(x, ++y, Align.TIGHT));

		add(new MenuField(1, FieldType.KEY, Action.LEFT2), new GBC(++x, y = 1, Align.TIGHT));
		add(new MenuField(1, FieldType.KEY, Action.RIGHT2), new GBC(x, ++y, Align.TIGHT));
		add(new MenuField(1, FieldType.KEY, Action.JETPACK2), new GBC(x, ++y, Align.TIGHT));
		add(new MenuField(1, FieldType.KEY, Action.SHOOT2), new GBC(x, ++y, Align.TIGHT));
		add(new MenuField(1, FieldType.KEY, Action.CYCLE_WEAPON2), new GBC(x, ++y, Align.TIGHT));
		add(new MenuField(1, FieldType.KEY, Action.ANGLE_CLOCKWISE2), new GBC(x, ++y, Align.TIGHT));
		add(new MenuField(1, FieldType.KEY, Action.ANGLE_COUNTERCLOCKWISE2), new GBC(x, ++y, Align.TIGHT));

		++y;
		add(new MenuButton(Labels.DEFAULT_LAN, this, game), new GBC(x = 0, ++y, Align.MID).setSpan(1, 1));
		add(new MenuButton(Labels.DEFAULT, this, game), new GBC(++x, y, Align.MID).setSpan(1, 1));
		add(new MenuButton(Labels.RETURN, this, game), new GBC(++x, y, Align.MID).setSpan(1, 1));
	}
}
