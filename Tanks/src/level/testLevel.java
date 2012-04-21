package level;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import entity.Entity;
import entity.Shell;
import entity.Tank;

import inputhandler.InputHandler;
import baseGame.BaseGame;
import baseGame.testGame;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;

public class testLevel extends BasicLevel {

	RGBImage tank;
	Entity tankEntity;
	Random rand = new Random();
	public testLevel(BaseGame game, InputHandler handler) {
		super(game, handler);

		tankEntity = new Tank(400, 200, 1, handler, this);
		addEntity(tankEntity);
		tankEntity.setSpeed(0, 1);
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
		tank = new RGBImage(new File("./res/Tank_Flat.png"));
		terrain = new Terrain(new File("./res/testlvl.png"));
		for (int i=0; i < 50; i++){
			addEntity(new Shell(500, 50, 5, this, rand.nextDouble() , rand.nextInt(360)));
		}
		// terrain = new Terrain(new File("./res/Cityscape_terrain.png"));
//		terrain.addExplosion(400, 300, 100);
//		terrain.addExplosion(400, 400, 50);
//		terrain.addExplosion(380, 410, 40);
//		terrain.addExplosion(340, 430, 30);
//		terrain.addExplosion(350, 440, 10);
	}

	public void tick() {
		super.tick();
	}

	public void onDraw(Renderer renderer) {
		super.render(renderer);

		renderer.DrawImage(terrain, testGame.ALPHA_MASK, 0, 0,
				terrain.getWidth(), terrain.getHeight());

		renderer.DrawImage(tank, -1, (int) tankEntity.getLocation()[0]
				- (int) tankEntity.getXr(), (int) tankEntity.getLocation()[1]
				- (int) tankEntity.getYr() + 1, tank.getWidth(),
				tank.getHeight());
	}

}
