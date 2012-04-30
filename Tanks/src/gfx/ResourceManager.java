package gfx;

import java.awt.Color;
import level.Terrain;

import baseGame.Rendering.RGBImage;

public class ResourceManager {

	private static Terrain TERRAIN;
	private static RGBImage BACKGROUND;

	public static RGBImage TANK1 = new RGBImage("/graphics/Tank2.png");
	public static RGBImage TANK2 = new RGBImage("/graphics/Tank2.png");
	public static RGBImage TANK3 = new RGBImage("/graphics/Tank2.png");
	public static RGBImage TANK4 = new RGBImage("/graphics/Tank2.png");
	public static RGBImage SHELL = new RGBImage("/graphics/Shell_temp.png");
	public static RGBImage SCOREBUBBLE = new RGBImage("/graphics/Scorebubble.png");
	public static RGBImage CROSSHAIR1 = new RGBImage("/graphics/Crosshair.png");
	public static RGBImage CROSSHAIR2 = new RGBImage("/graphics/Crosshair.png");
	public static RGBImage CROSSHAIR3 = new RGBImage("/graphics/Crosshair.png");
	public static RGBImage CROSSHAIR4 = new RGBImage("/graphics/Crosshair.png");
	public static RGBImage ROCKET = new RGBImage("/graphics/Rocket_sheet.png");
	public static RGBImage MINE = new RGBImage("/graphics/Mine_sheet.png");
	public static RGBImage GRENADE = new RGBImage("/graphics/Grenade_temp.png");
	public static RGBImage PACKAGE = new RGBImage("/graphics/Package.png");
	public static RGBImage BUBBLEHEARTH = new RGBImage("/graphics/BubbleHearth.png");
	public static RGBImage AIRSTRIKEBEACON = new RGBImage("/graphics/AirStrikeBeacon.png");
	public static RGBImage BULLET = new RGBImage("/graphics/Bullet.png");
	public static RGBImage HEART = new RGBImage("/graphics/Heart.png");
	public static Color COLORMASK = new Color(0x00FAE1);

	private static ResourceManager instance = null;

	public static ResourceManager getInstance() {
		if (instance == null)
			instance = new ResourceManager();
		return instance;
	}

	private ResourceManager() {
	}

	public static Terrain getTerrain() {
		return TERRAIN;
	}

	public static void setTerrain(Terrain terrain) {
		ResourceManager.TERRAIN = terrain;
	}

	public static RGBImage getBackGround() {
		return BACKGROUND;
	}

	public static void setBackGround(RGBImage backGround) {
		ResourceManager.BACKGROUND = backGround;
	}

}
