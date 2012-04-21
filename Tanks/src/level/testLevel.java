package level;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import entity.Entity;
import entity.Tank;

import inputhandler.InputHandler;
import baseGame.BaseGame;
import baseGame.testGame;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;

public class testLevel extends BasicLevel {

	RGBImage tank;
	Entity tankEntity;
	
	public testLevel(BaseGame game, InputHandler handler) {
		super(game, handler);
		tankEntity = new Tank(400,200);
		addEntity(tankEntity);
		tankEntity.setSpeed(0, 1);
		tank = new RGBImage(new BufferedImage(40, 60,
				BufferedImage.TYPE_INT_RGB));
		// TODO Auto-generated constructor stub
	
		
		for(int ii = 0; ii<tank.getPixels().length;ii++){
			tank.getPixels()[ii] = 0xFF0000;
			
		}
		
		terrain = new Terrain(new File("./res/temp_ground.png"));
	
	}
	public void tick(){
		super.tick();
		
		
	}
	public void onDraw(Renderer renderer){
		
		super.render(renderer);
		renderer.DrawImage(terrain,testGame.ALPHA_MASK, 0, 0, 800, 600);
		renderer.DrawImage(tank, (int)tankEntity.getLocation()[0], (int)tankEntity.getLocation()[1], 40, 60);
	}
	

	
	
	
}
