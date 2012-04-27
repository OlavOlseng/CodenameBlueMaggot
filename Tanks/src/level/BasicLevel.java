package level;

import inputhandler.InputHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sound.SoundEffect;

import baseGame.BaseGame;
import baseGame.Rendering.Renderer;
import baseGame.animations.AnimationFactory;

import entity.*;

public abstract class BasicLevel {

	protected boolean gameOver = false;
	protected List<Entity> entities;
	protected ArrayList<Tank> players;
	protected InputHandler handler;
	protected BaseGame game;
	protected Terrain terrain;
	private Random rand = new Random();
	protected List<FloatingPoint> playerSpawns;
	protected List<FloatingPoint> bubbleSpawns;
	
	
	public BasicLevel(BaseGame game, InputHandler handler) {
		this.game = game;
		this.handler = handler;
		players = new ArrayList<Tank>();
		initLevel();
		SoundEffect.init();
		
	}

	public Terrain getTerrain() {
		return terrain;
	}
	
	public void initLevel() {
		entities = new ArrayList<Entity>();
		AnimationFactory.getInstance().addSpriteSheet(new File("./res/Explosion1.png"), Animations.EXPLOSIONS, 50, 50);
		AnimationFactory.getInstance().addSpriteSheet(new File("./res/Explosion2.png"), Animations.EXPLOSIONS2, 100,
				100);
		playerSpawns = new ArrayList<FloatingPoint>();
		bubbleSpawns = new ArrayList<FloatingPoint>();
	}
	
//	requires you to init bubbleSpawns and playerSpawns
	abstract public void initSpawn();
	
	public List<FloatingPoint> getPlayerSpawns(){
		return playerSpawns;
	}
	public List<FloatingPoint> getBubbleSpawns(){
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
	
	public void checkGameOver(){
		int playerAliveCount = 0;
		for (Tank player : players) {
			if (!player.removed)
				playerAliveCount++;

		}
		if (playerAliveCount <= 1)
			gameOver = true;
		for (Tank player : players) {
			if (!player.removed)
				player.addScore(1000);
		}
	}

	public void render(Renderer renderer) {
		for (Entity ent : entities) {
			ent.render(renderer);
		}
	}

	public void tick(double dt) {

//		handler.tick(dt);
		if (handler.grenadeSpam.clicked) {
			for (int i = 0; i < 100; i++) {
				addEntity(new Grenade(500, 50, this, rand.nextDouble(), rand.nextInt(360)));
			}
		}
		// ticks all the ents
		for (int i = 0; i < entities.size(); i++) {
			Entity ent = entities.get(i);
			ent.tick(dt);
			if (ent.removed) {
				removeEntity(ent);
				i--;
				continue;
			}
		}
		checkGameOver();
	}
}