package level;

import inputhandler.InputHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import baseGame.BaseGame;
import baseGame.Rendering.Renderer;
import baseGame.animations.AnimationFactory;

import entity.*;

public class BasicLevel {

	protected List<Entity> entities;
	protected ArrayList<Tank> players;
	protected InputHandler handler;
	protected BaseGame game;
	protected Terrain terrain;
	private Random rand = new Random();

	public BasicLevel(BaseGame game, InputHandler handler) {
		this.game = game;
		this.handler = handler;
		players = new ArrayList<Tank>();
		initLevel();
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void initLevel() {
		entities = new ArrayList<Entity>();
		AnimationFactory.getInstance().addSpriteSheet(new File("./res/Explosion1.png"), Animations.EXPLOSIONS, 50, 50);
		AnimationFactory.getInstance().addSpriteSheet(new File("./res/Explosion2.png"), Animations.EXPLOSIONS2, 100, 100);
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

	public void render(Renderer renderer) {
		for (Entity ent : entities) {
			ent.render(renderer);
		}
	}

	public void tick(double dt) {

		handler.tick(dt);
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
	}
}