package gfx;

import java.awt.Color;
import level.Terrain;

import baseGame.Rendering.RGBImage;

public class ResourceManager {

	private static Terrain TERRAIN;
	private static RGBImage BACKGROUND;


	public static final RGBImage TANK1 = new RGBImage("/graphics/Tank2.png");
	public static final RGBImage TANK2 = new RGBImage("/graphics/Tank2.png");
	public static final RGBImage TANK3 = new RGBImage("/graphics/Tank2.png");
	public static final RGBImage TANK4 = new RGBImage("/graphics/Tank2.png");
	public static final RGBImage SHELL = new RGBImage("/graphics/Shell_temp.png");
	public static final RGBImage SCOREBUBBLE = new RGBImage("/graphics/Scorebubble.png");
	public static final RGBImage CROSSHAIR1 = new RGBImage("/graphics/Crosshair.png");
	public static final RGBImage CROSSHAIR2 = new RGBImage("/graphics/Crosshair.png");
	public static final RGBImage CROSSHAIR3 = new RGBImage("/graphics/Crosshair.png");
	public static final RGBImage CROSSHAIR4 = new RGBImage("/graphics/Crosshair.png");
	public static final RGBImage ROCKET = new RGBImage("/graphics/Rocket_sheet.png");
	public static final RGBImage MINE = new RGBImage("/graphics/Mine_sheet.png");
	public static final RGBImage GRENADE = new RGBImage("/graphics/Grenade_temp.png");
	public static final RGBImage PACKAGE = new RGBImage("/graphics/Package.png");
	public static final RGBImage BUBBLEHEARTH = new RGBImage("/graphics/BubbleHearth.png");
	public static final RGBImage AIRSTRIKEBEACON = new RGBImage("/graphics/AirStrikeBeacon.png");
	public static final RGBImage BULLET = new RGBImage("/graphics/Bullet.png");
	public final RGBImage HEART = new RGBImage("/graphics/Heart.png");
	public static final Color COLORMASK = new Color(0x00FAE1);


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
