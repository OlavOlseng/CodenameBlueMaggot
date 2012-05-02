package gfx;

import gfx.GBC.Align;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import blueMaggot.BlueMaggot;
import blueMaggot.Game;
import blueMaggot.GameState;

public class MenuLevelSelect extends Menu {

	public File path = new File("./lvl/");

	private ArrayList<BufferedImage> lvls = new ArrayList<BufferedImage>();
	public ArrayList<MenuLevelButton> lvlBtns = new ArrayList<MenuLevelButton>();

	protected File selectedLevelTerrain;
	protected File selectedLevelBackground;

	public MenuLevelSelect(Game game) {
		setVisible(false);
		int gridx = 0;
		int gridy = 0;
		int lvlCount = 0;

		if (path.exists()) {
			for (File lvl : path.listFiles()) {
				if (lvl.getName().contains("terrain")) {
					if (lvlCount > 12)
						BlueMaggot.e = new Exception("Too many levels added! Max is 12.");
					MenuLevelButton btnLvl = new MenuLevelButton(lvl, this);
					if (gridx <= 3) {
						gridx++;
					} else {
						gridx = 1;
						++gridy;
					}
					add(btnLvl, new GBC(gridx, gridy, Align.MID));
					lvlBtns.add(btnLvl);
					lvlCount++;
				}
			}
		} else
			BlueMaggot.e = new FileNotFoundException("\nPath: " + path.getAbsolutePath());

		add(new MenuButton(Labels.APPLY, this, game), new GBC(1, ++gridy, Align.MID).setSpan(2, 1));
		add(new MenuButton(Labels.RETURN, this, game), new GBC(3, gridy, Align.MID).setSpan(2, 1));
	}

	public void apply() {
		GameState.getInstance().setSelectedLevelTerrain(selectedLevelTerrain);
		GameState.getInstance().setSelectedLevelBackground(selectedLevelBackground);
		System.out.println("selected level: " + GameState.getInstance().getSelectedLevelTerrain());
		System.out.println("selected level background: " + GameState.getInstance().getSelectedLevelBackground());
	}
}
