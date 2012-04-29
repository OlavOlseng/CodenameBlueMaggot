package gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

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
		
		for (File lvl : path.listFiles()) {
			if (lvl.getName().contains("terrain")) {
				MenuLevelButton btnLvl = new MenuLevelButton(lvl, this);
				if (gridx <= 3) {
					add(btnLvl, new GBC(gridx, gridy, null).setInsets(10, 10, 10, 10));
					lvlBtns.add(btnLvl);
					++gridx;
				} else {
					gridx = 0;
					++gridy;
					add(btnLvl, new GBC(gridx, gridy, null).setInsets(10, 10, 10, 10));
					lvlBtns.add(btnLvl);
				}
			}
		}
		
		add(new MenuButton("apply", this, game), new GBC(0, ++gridy, "left").setInsets(10, 10, 10, 10).setSpan(2, 1));
		add(new MenuButton("return", this, game), new GBC(2, gridy, "left").setInsets(10, 10, 10, 10).setSpan(2, 1));
	}

	public void apply() {
		GameState.getInstance().selectedLevelTerrain = selectedLevelTerrain;
		GameState.getInstance().selectedLevelBackground = selectedLevelBackground;
		System.out.println("selected level: " + GameState.getInstance().selectedLevelTerrain);
		System.out.println("selected level background: " + GameState.getInstance().selectedLevelBackground);
	}
}
