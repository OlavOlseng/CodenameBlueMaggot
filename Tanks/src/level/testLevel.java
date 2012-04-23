package level;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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

	private RGBImage tank;
	private RGBImage shell;
	private RGBImage muzzle1;
	private RGBImage crossHair;
	private int muzzle1Rotation;
	
	private RGBImage muzzle2;
	private Tank tankEntity;
	
	Random rand = new Random();
	public testLevel(BaseGame game, InputHandler handler) {
		super(game, handler);

		tankEntity = new Tank(10, 10, 1, handler, this);
		addEntity(tankEntity);
		tankEntity.setSpeed(0, 1);
		System.out.println(entities.size());
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
	
		shell = new RGBImage(new File("./res/Shell_temp.png"));
		tank = new RGBImage(new File("./res/Tank_Flat.png"));
		terrain = new Terrain(new File("./res/testlvl.png"));
		crossHair = new RGBImage(new File("./res/Crosshair.png"));

		}
//		
	public void tick() {
		super.tick();
		
	}

	public void onDraw(Renderer renderer) {
		super.render(renderer);
		
		for (Entity ent : entities) {
			if (ent instanceof Shell){
				renderer.DrawImage(shell, -1, (int)(ent.getLocation()[0] - ent.getXr()), (int)(ent.getLocation()[1]- ent.getYr()), shell.getWidth(), shell.getHeight());
			}
			
		}
		renderer.DrawImage(terrain,testGame.ALPHA_MASK, 0, 0,terrain.getWidth(), terrain.getHeight());
		renderer.DrawImage(tank,-1, (int)tankEntity.getLocation()[0] - (int)tankEntity.getXr(), (int)tankEntity.getLocation()[1] -(int)tankEntity.getYr() +1, tank.getWidth(), tank.getHeight());
		renderer.DrawImage(crossHair,-1,(int) tankEntity.getCrosshairLocation().getX()-10, (int)tankEntity.getCrosshairLocation().getY()-10,crossHair.getWidth(), crossHair.getHeight());
		}

}
