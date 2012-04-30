package gfx;

import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;

import level.Terrain;

import baseGame.Rendering.RGBImage;

public class ResourceManager {

	private static Terrain TERRAIN;
	private static RGBImage BACKGROUND;

	public static Color COLORMASK;
	public static RGBImage BULLET;
	public static RGBImage AIRSTRIKEBEACON;
	public static RGBImage BUBBLEHEARTH;
	public static RGBImage PACKAGE;
	public static RGBImage GRENADE;
	public static RGBImage MINE;
	public static RGBImage ROCKET;
	public static RGBImage CROSSHAIR4;
	public static RGBImage CROSSHAIR3;
	public static RGBImage CROSSHAIR2;
	public static RGBImage CROSSHAIR1;
	public static RGBImage SCOREBUBBLE;
	public static RGBImage SHELL;
	public static RGBImage HEART;
	public static RGBImage TANK4;
	public static RGBImage TANK3;
	public static RGBImage TANK2;
	public static RGBImage TANK1;

	public void initResources() {
		try {
			
			TANK1 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Tank2.png")));
			TANK2 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Tank2.png")));
			TANK3 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Tank2.png")));
			TANK4 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Tank2.png")));
			SHELL = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Shell_temp.png")));
			SCOREBUBBLE = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Scorebubble.png")));
			CROSSHAIR1 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Crosshair.png")));
			CROSSHAIR2 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Crosshair.png")));
			CROSSHAIR3 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Crosshair.png")));
			CROSSHAIR4 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Crosshair.png")));
			ROCKET = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Rocket_sheet.png")));
			MINE = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Mine_sheet.png")));
			GRENADE = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Grenade_temp.png")));
			PACKAGE = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Package.png")));
			BUBBLEHEARTH = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/BubbleHearth.png")));
			AIRSTRIKEBEACON = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/AirStrikeBeacon.png")));
			BULLET = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Bullet.png")));
			HEART = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Heart.png")));
			COLORMASK = new Color(0x00FAE1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

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
