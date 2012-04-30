package blueMaggot.maps;

import java.io.IOException;
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
import blueMaggot.BlueMaggot;
import blueMaggot.GameState;

public class cityScape extends BasicLevel {

	Random rand = new Random();
	protected double totalElapsedTime = 0;

	public cityScape(BaseGame game, InputHandler handler) {
		super(game, handler);

		try {
			terrain = new Terrain(GameState.getInstance().getSelectedLevelTerrain());
			ResourceManager.setTerrain(terrain);
			ResourceManager.setBackGround(new RGBImage(GameState.getInstance().getSelectedLevelBackground()));
		} catch (IOException e) {
			BlueMaggot.e = e;
		}
		SoundEffect.SPAWN.play();

	}

	public void init() {
		initSpawn();
		addPlayers();
		SoundEffect.SPAWN.play();

		if (shouldSpawnBubbleHearth())
			addEntity(new BubbleHearth(bubbleSpawns.getPoint(), this));

		if (shouldSpawnCrate())
			addEntity(new Package(bubbleSpawns.getPoint(), this));

	}

	public void addPlayers() {

		addEntity(new Tank(playerSpawns.getPoint(), 1, handler, this));
		addEntity(new Tank(playerSpawns.getPoint(), 2, handler, this));
	}

	@Override
	public void initSpawn() {
		playerSpawns.addPoint(new FloatingPoint(100, 10));
		playerSpawns.addPoint(new FloatingPoint(160, 10));
		playerSpawns.addPoint(new FloatingPoint(530, 10));
		playerSpawns.addPoint(new FloatingPoint(750, 10));
		playerSpawns.addPoint(new FloatingPoint(1120, 10));
		playerSpawns.addPoint(new FloatingPoint(1170, 10));

		bubbleSpawns.addPoint(new FloatingPoint(80, 10));
		bubbleSpawns.addPoint(new FloatingPoint(180, 10));
		bubbleSpawns.addPoint(new FloatingPoint(600, 10));
		bubbleSpawns.addPoint(new FloatingPoint(700, 10));
		bubbleSpawns.addPoint(new FloatingPoint(1100, 10));
		bubbleSpawns.addPoint(new FloatingPoint(1200, 10));
	}

	public void spawnBubble() {
		this.addEntity(new ScoreBubble(bubbleSpawns.getPoint(), this, 0.5, rand.nextInt(360), 100));
		SoundEffect.SPAWN.play();
	}

	public void spawnBubbleHearth() {
		this.addEntity(new BubbleHearth(bubbleSpawns.getPoint(), this));
		SoundEffect.SPAWN.play();
	}

	public void spawnCrate() {
		this.addEntity(new Package(bubbleSpawns.getPoint(), this));
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
		totalElapsedTime += dt;
	}

	protected boolean shouldSpawnBubble() {
		int ticket = rand.nextInt(300);
		return ticket == 5;
	}

	protected boolean shouldSpawnCrate() {
		return (totalElapsedTime % 1200 < 2);
	}

	protected boolean shouldSpawnBubbleHearth() {
		return (totalElapsedTime % 1500 < 1);
	}

	public void onDraw(Renderer renderer) {
		// draws backgorund recursively

		renderer.DrawImage(ResourceManager.getBackGround(), 0, 0, GameState.getInstance().getWidth(), GameState.getInstance().getHeight());
		renderer.DrawImage(ResourceManager.getTerrain(), -1, 0, 0, terrain.getWidth(), terrain.getHeight());
		super.render(renderer);
	}
}
