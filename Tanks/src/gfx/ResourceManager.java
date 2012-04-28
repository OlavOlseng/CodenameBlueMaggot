package gfx;

import java.awt.Color;
import java.io.File;

import level.Terrain;

import baseGame.Rendering.RGBImage;

public class ResourceManager {
	public static final RGBImage TANK1 = new RGBImage(new File("./res/graphics/Tank2.png"));
	public static final RGBImage TANK2 = new RGBImage(new File("./res/graphics/Tank2.png"));
	public static final RGBImage TANK3 = new RGBImage(new File("./res/graphics/Tank2.png"));
	public static final RGBImage TANK4 = new RGBImage(new File("./res/graphics/Tank2.png"));
	public static final RGBImage SHELL = new RGBImage(new File("./res/graphics/Shell_temp.png"));
	public static final RGBImage SCOREBUBBLE = new RGBImage(new File("./res/graphics/Scorebubble.png"));
	public static final RGBImage CROSSHAIR1 = new RGBImage(new File("./res/graphics/Crosshair.png"));
	public static final RGBImage CROSSHAIR2 = new RGBImage(new File("./res/graphics/Crosshair.png"));
	public static final RGBImage CROSSHAIR3 = new RGBImage(new File("./res/graphics/Crosshair.png"));
	public static final RGBImage CROSSHAIR4 = new RGBImage(new File("./res/graphics/Crosshair.png"));
	public static final RGBImage ROCKET = new RGBImage(new File("./res/graphics/Rocket_sheet.png"));
	public static final RGBImage MINE = new RGBImage(new File("./res/graphics/Mine_sheet.png"));
	public static final RGBImage GRENADE = new RGBImage(new File("./res/graphics/Grenade_temp.png"));
	public static final RGBImage PACKAGE = new RGBImage(new File("./res/graphics/Package.png"));
	public static final RGBImage BUBBLEHEARTH = new RGBImage(new File("./res/graphics/BubbleHearth.png"));
	public static final RGBImage AIRSTRIKEBEACON = new RGBImage(new File("./res/graphics/AirStrikeBeacon.png"));
	public static final RGBImage BULLET = new RGBImage(new File("./res/graphics/Bullet.png"));
	public static final Color COLORMASK = new Color(0x00FAE1);
	
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
