package gfx;

import java.io.File;

import level.Terrain;

import baseGame.Rendering.RGBImage;

public class ResourceManager {
	public static final RGBImage TANK1 = new RGBImage(new File("./res/Tank2.png"));
	public static final RGBImage TANK2 = new RGBImage(new File("./res/Tank2.png"));
	public static final RGBImage SHELL = new RGBImage(new File("./res/Shell_temp.png"));
	public static final RGBImage SCOREBUBBLE = new RGBImage(new File("./res/Scorebubble.png"));
	public static final RGBImage CROSSHAIR1 = new RGBImage(new File("./res/Crosshair.png"));
	public static final RGBImage CROSSHAIR2 = new RGBImage(new File("./res/Crosshair.png"));
	public static final RGBImage ROCKET = new RGBImage(new File("./res/Rocket_sheet.png"));
	public static final RGBImage GRENADE = new RGBImage(new File("./res/Grenade_temp.png"));
	
	private static Terrain terrain;
	private static RGBImage backGround;

	public static Terrain getTerrain() {
		return terrain;
	}

	public static void setTerrain(Terrain terrain) {
		ResourceManager.terrain = terrain;
	}

	public static RGBImage getBackGround() {
		return backGround;
	}

	public static void setBackGround(RGBImage backGround) {
		ResourceManager.backGround = backGround;
	}

}
