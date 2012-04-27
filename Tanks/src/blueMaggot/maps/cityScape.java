package blueMaggot.maps;

import java.io.File;
import java.util.Random;

import sound.SoundEffect;

import level.BasicLevel;
import level.Terrain;

import entity.FloatingPoint;
import entity.ScoreBubble;
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
		initSpawn();

		addEntity(new Tank(playerSpawns.get(rand.nextInt(playerSpawns.size())), 1, handler, this));
		addEntity(new Tank(playerSpawns.get(rand.nextInt(playerSpawns.size())), 2, handler, this));
		SoundEffect.SPAWN.play();
	}

	@Override
	public void initSpawn() {
		playerSpawns.add(new FloatingPoint(30, 10));
		playerSpawns.add(new FloatingPoint(180, 10));
		playerSpawns.add(new FloatingPoint(512, 10));
		playerSpawns.add(new FloatingPoint(844, 10));
		playerSpawns.add(new FloatingPoint(996, 10));

		bubbleSpawns.add(new FloatingPoint(100, 10));
		bubbleSpawns.add(new FloatingPoint(512, 10));
		bubbleSpawns.add(new FloatingPoint(896, 10));
	}

	public void spawnBubble() {
		this.addEntity(new ScoreBubble(bubbleSpawns.get(rand.nextInt(bubbleSpawns.size())), 4, this, 0.5, rand
				.nextInt(360), 100));
		SoundEffect.SPAWN.play();
	}

	public void tick(double dt) {
		super.tick(dt);
		int ticket = rand.nextInt(600);
		if (ticket == 5) {
			spawnBubble();
		}
	}

	public void onDraw(Renderer renderer) {
		// draws backgorund recursively
		renderer.DrawImage(ResourceManager.getBackGround(), 0, 0, Game.WIDTH, Game.HEIGHT);
		renderer.DrawImage(ResourceManager.getTerrain(), -1, 0, 0, terrain.getWidth(), terrain.getHeight());
		super.render(renderer);
	}
}
