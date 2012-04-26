package blueMaggot.maps;

import java.io.File;
import java.util.Random;

import level.BasicLevel;
import level.Terrain;

import entity.Tank;
import gfx.ResourceManager;

import inputhandler.InputHandler;
import baseGame.BaseGame;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import blueMaggot.Game;

public class cityScape extends BasicLevel {

	Random rand = new Random();

	public cityScape(BaseGame game, InputHandler handler) {
		super(game, handler);
		terrain = new Terrain(new File("./res/Cityscape_terrain.png"));
		ResourceManager.setTerrain(terrain);
		
		ResourceManager.setBackGround(new RGBImage(new File("./res/Cityscape_background.png")));
		
		addEntity(new Tank(10, 10, 1, handler, this));
		addEntity(new Tank(1000, 10, 2, handler, this));
	}	

	public void tick(double dt) {
		super.tick(dt);
	}

	public void onDraw(Renderer renderer) {
		// draws backgorund recursively
		renderer.DrawImage(ResourceManager.getBackGround(), 0, 0, Game.WIDTH, Game.HEIGHT);
		renderer.DrawImage(ResourceManager.getTerrain(), -1, 0, 0, terrain.getWidth(), terrain.getHeight());
		super.render(renderer);
	}
}
