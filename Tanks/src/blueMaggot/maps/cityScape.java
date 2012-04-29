package blueMaggot.maps;

import java.util.Random;

import sound.SoundEffect;

import level.BasicLevel;
import level.Terrain;

import entity.BubbleHearth;
import entity.FloatingPoint;
import entity.Package;
import entity.ScoreBubble;
import entity.Tank;
import gfx.ResourceManager;

import inputhandler.InputHandler;
import baseGame.BaseGame;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import blueMaggot.GameState;

public class cityScape extends BasicLevel {

	Random rand = new Random();

	public cityScape(BaseGame game, InputHandler handler) {
		super(game, handler);
		terrain = new Terrain(GameState.getInstance().selectedLevelTerrain);
		ResourceManager.setTerrain(terrain);
		ResourceManager.setBackGround(new RGBImage(GameState.getInstance().selectedLevelBackground));
		SoundEffect.SPAWN.play();
	}

	public void init() {
		initSpawn();
		addPlayers();

		SoundEffect.SPAWN.play();

<<<<<<< HEAD
		addEntity(new BubbleHearth(bubbleSpawns.get(1), this));
		addEntity(new Package(bubbleSpawns.get(2), this));
=======

>>>>>>> 9051e7f39b2aaf39404344734cfcfc0a5dbabc87
	}

	public void addPlayers() {
		addEntity(new Tank(playerSpawns.get(rand.nextInt(playerSpawns.size())), 1, handler, this));
		addEntity(new Tank(playerSpawns.get(rand.nextInt(playerSpawns.size())), 2, handler, this));
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
		this.addEntity(new ScoreBubble(bubbleSpawns.get(rand.nextInt(bubbleSpawns.size())), this, 0.5, rand.nextInt(360), 100));
		SoundEffect.SPAWN.play();
	}
	public void spawnBubbleHearth() {
		this.addEntity(new BubbleHearth(bubbleSpawns.get(rand.nextInt(bubbleSpawns.size())), this));
		SoundEffect.SPAWN.play();
	}
	public void spawnCrate() {
		this.addEntity(new Package(bubbleSpawns.get(rand.nextInt(bubbleSpawns.size())), this));
		SoundEffect.SPAWN.play();
	}

	@Override
	public void tick(double dt) {
		super.tick(dt);
		if (shouldSpawnBubble()) {
			spawnBubble();
		}
		if (shouldSpawnCrate()) {
			spawnCrate();
		}
		if (shouldSpawnBubbleHearth()) {
			spawnBubbleHearth();
		}
	}

	protected boolean shouldSpawnBubble() {
		int ticket = rand.nextInt(300);
		return ticket == 5;
	}

	protected boolean shouldSpawnCrate() {
		int ticket = rand.nextInt(500);
		return ticket == 10;
	}

	protected boolean shouldSpawnBubbleHearth() {
		int ticket = rand.nextInt(800);
		return ticket == 100;
	}

	public void onDraw(Renderer renderer) {
		// draws backgorund recursively
		renderer.DrawImage(ResourceManager.getBackGround(), 0, 0, GameState.getInstance().width, GameState.getInstance().height);
		renderer.DrawImage(ResourceManager.getTerrain(), -1, 0, 0, terrain.getWidth(), terrain.getHeight());
		super.render(renderer);
	}
}
