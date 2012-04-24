package level;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import entity.Entity;
import entity.Grenade;
import entity.ScoreBubble;
import entity.Shell;
import entity.Tank;

import inputhandler.InputHandler;
import baseGame.BaseGame;
import baseGame.testGame;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;

public class testLevel extends BasicLevel {

	private RGBImage tank1;
	private RGBImage tank2;
	private RGBImage shell;
	private RGBImage muzzle1;
	private RGBImage crossHair1;
	private RGBImage crossHair2;
	private RGBImage grenade;
	private RGBImage scoreBubble;
	private int muzzle1Rotation;

	private RGBImage muzzle2;
	private Tank tankEntity1;
	private Tank tankEntity2;

	Random rand = new Random();

	public testLevel(BaseGame game, InputHandler handler) {
		super(game, handler);

		tankEntity1 = new Tank(10, 10, 1, handler, this);
		addEntity(tankEntity1);
		tankEntity2 = new Tank(1000, 10, 2, handler, this);
		addEntity(tankEntity2);
		/*
		 * tank = new RGBImage(new BufferedImage(16,
		 * 12,BufferedImage.TYPE_INT_RGB)); // TODO Auto-generated constructor
		 * stub
		 * 
		 * 
		 * for(int ii = 0; ii<tank.getPixels().length;ii++){
		 * tank.getPixels()[ii] = 0xFF0000;
		 * 
		 * }
		 */

		scoreBubble = new RGBImage(new File("./res/Scorebubble.png"));
		grenade = new RGBImage(new File("./res/Grenade_temp.png"));
		shell = new RGBImage(new File("./res/Shell_temp.png"));
		tank1 = new RGBImage(new File("./res/Tank_Flat.png"));
		tank2 = new RGBImage(new File("./res/Tank_Flat.png"));
		// terrain = new Terrain(new File("./res/testlvl.png"));
		terrain = new Terrain(new File("./res/Cityscape_terrain.png"));
		crossHair1 = new RGBImage(new File("./res/Crosshair.png"));
		crossHair2 = new RGBImage(new File("./res/Crosshair.png"));

	}

	//
	public void tick() {
		super.tick();
		addEntity(new ScoreBubble(rand.nextInt(1000), 10, 5, this, 0.3, 0, 100));
	}

	public void onDraw(Renderer renderer) {
		super.render(renderer);

		for (Entity ent : entities) {
			if (ent instanceof Shell) {
				renderer.DrawImage(shell, -1,
						(int) (ent.getLocation()[0] - ent.getXr()),
						(int) (ent.getLocation()[1] - ent.getYr()),
						shell.getWidth(), shell.getHeight());
			}
			if (ent instanceof Grenade) {
				renderer.DrawImage(grenade, -1,
						(int) (ent.getLocation()[0] - ent.getXr()),
						(int) (ent.getLocation()[1] - ent.getYr()),
						grenade.getWidth(), grenade.getHeight());
			}
			if (ent instanceof ScoreBubble) {
				renderer.DrawImage(scoreBubble, -1,
						(int) (ent.getLocation()[0] - ent.getXr()),
						(int) (ent.getLocation()[1] - ent.getYr()),
						scoreBubble.getWidth(), scoreBubble.getHeight());
			}
		}
		renderer.DrawImage(terrain, testGame.ALPHA_MASK, 0, 0,
				terrain.getWidth(), terrain.getHeight());
		renderer.DrawImage(tank1, -1, (int) tankEntity1.getLocation()[0]
				- (int) tankEntity1.getXr(), (int) tankEntity1.getLocation()[1]
				- (int) tankEntity1.getYr() + 1, tank1.getWidth(),
				tank1.getHeight());
		renderer.DrawImage(tank2, -1, (int) tankEntity2.getLocation()[0]
				- (int) tankEntity2.getXr(), (int) tankEntity2.getLocation()[1]
				- (int) tankEntity2.getYr() + 1, tank2.getWidth(),
				tank2.getHeight());
		renderer.DrawImage(
				crossHair1,
				-1,
				(int) tankEntity1.getCrosshairLocation().getX()
						- crossHair1.getWidth() / 2,
				(int) tankEntity1.getCrosshairLocation().getY()
						- crossHair1.getHeight() / 2, crossHair1.getWidth(),
				crossHair1.getHeight());
		renderer.DrawImage(
				crossHair2,
				-1,
				(int) tankEntity2.getCrosshairLocation().getX()
						- crossHair2.getWidth() / 2,
				(int) tankEntity2.getCrosshairLocation().getY()
						- crossHair2.getHeight() / 2, crossHair2.getWidth(),
				crossHair2.getHeight());
	}

}
