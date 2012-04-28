package gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import blueMaggot.Game;

public class MenuLevelSelect extends Menu {

	public File path = new File("./lvl");

	private ArrayList<BufferedImage> lvls = new ArrayList<BufferedImage>();

	protected File selectedLevelTerrain;
	protected File selectedLevelBackground;

	public MenuLevelSelect(Game game) {
		setVisible(false);
		int gridx = 0;
		int gridy = 0;
		System.out.println(path.listFiles());
		for (File lvl : path.listFiles()) {
			if (lvl.getName().contains("terrain"))
				if (gridx <= 3) {
					add(new MenuLevelButton(lvl, this), new GBC(gridx, gridy, null).setInsets(10, 10, 10, 10));
					++gridx;
				} else {
					gridx = 0;
					++gridy;
					add(new MenuLevelButton(lvl, this), new GBC(gridx, gridy, null).setInsets(10, 10, 10, 10));
				}
		}
		add(new MenuButton("apply", this, game), new GBC(0, ++gridy, "left").setInsets(10, 10, 10, 10).setSpan(2, 1));
		add(new MenuButton("return", this, game), new GBC(2, gridy, "left").setInsets(10, 10, 10, 10).setSpan(2, 1));
	}

	public void apply() {
		Game.SELECTED_LEVEL_TERRAIN = selectedLevelTerrain;
		Game.SELECTED_LEVEL_BACKGROUND = selectedLevelBackground;
		System.out.println("selected level: " + Game.SELECTED_LEVEL_TERRAIN);
		System.out.println("selected level background: " + Game.SELECTED_LEVEL_BACKGROUND);
	}

	public void cacheThumb() {

	}
}
