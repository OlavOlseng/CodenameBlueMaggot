package level;

import gfx.Menu;
import gfx.ResourceManager;
import inputhandler.InputHandler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import sound.SoundEffect;

import baseGame.BaseGame;
import baseGame.Rendering.Renderer;
import baseGame.animations.AnimationFactory;
import blueMaggot.GameState;

import entity.*;

public abstract class BasicLevel {

	protected List<Entity> entities;
	protected ArrayList<Tank> players;
	protected InputHandler handler;
	protected BaseGame game;
	protected Terrain terrain;
	private Random rand = new Random();
	public SpawnTracker playerSpawns;
	public SpawnTracker bubbleSpawns;
	
	public BasicLevel(BaseGame game, InputHandler handler) {
		this.game = game;
		this.handler = handler;
		players = new ArrayList<Tank>();
		initLevel();
		SoundEffect.init();
		ResourceManager.TANK1.replaceColors(0x00FAE1, Menu.pinkDark.getRGB());
		ResourceManager.CROSSHAIR1.replaceColors(0x00FAE1, 0xFF2121);
		ResourceManager.TANK2.replaceColors(0x00FAE1, 0x87FF54);
		ResourceManager.CROSSHAIR2.replaceColors(0x00FAE1, 0xFF2121);

		entities = new ArrayList<Entity>();
		playerSpawns = new SpawnTracker();
		bubbleSpawns = new SpawnTracker();
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void initLevel() {
		entities = new ArrayList<Entity>();
		try {
			BufferedImage img1 = ImageIO.read(getClass().getResourceAsStream("/graphics/Explosion1.png"));
			BufferedImage img2 = ImageIO.read(getClass().getResourceAsStream("/graphics/Explosion2.png"));
			
			AnimationFactory.getInstance().addSpriteSheet(img1, Animations.EXPLOSIONS, 50, 50);
			AnimationFactory.getInstance().addSpriteSheet(img2, Animations.EXPLOSIONS2, 100, 100);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// requires you to init bubbleSpawns and playerSpawns
	abstract public void initSpawn();

	public SpawnTracker getPlayerSpawns() {
		return playerSpawns;
	}

	public SpawnTracker getBubbleSpawns() {
		return bubbleSpawns;
	}

	public void addEntity(Entity ent) {
		entities.add(ent);
	}

	public void removeEntity(Entity ent) {
		entities.remove(ent);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public ArrayList<Tank> getPlayers() {
		return players;
	}

	public void checkGameOver() {
		int playerAliveCount = 0;
		for (Tank player : players) {
			if (!player.removed)
				playerAliveCount++;
		}
		if (playerAliveCount <= 1) {
			GameState.getInstance().setGameOver(true);
		}
	}

	public void render(Renderer renderer) {
		for (Entity ent : entities) {
			ent.render(renderer);
		}
	}

	public void tick(double dt) {

		// handler.tick(dt);
		if (handler.grenadeSpam.clicked) {
			for (int i = 0; i < 100; i++) {
				addEntity(new Grenade(500, 50, this, rand.nextDouble(), rand.nextInt(360)));
			}
		}
		// ticks all the ents

		for (int i = 0; i < entities.size(); i++) {
			Entity ent = entities.get(i);

			if (ent.shouldTick()) {
				ent.tick(dt);
			}

			if (ent.removed) {
				removeEntity(ent);
				i--;
				continue;
			}

			checkGameOver();
		}
	}
}
