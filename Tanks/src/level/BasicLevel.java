package level;

import inputhandler.InputHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import baseGame.BaseGame;
import baseGame.Rendering.Renderer;

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
		init();
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void init() {
		entities = new ArrayList<Entity>();

	}

	public void addEntity(Entity ent) {
		if (ent instanceof Tank)
			players.add((Tank) ent);
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

		// Denne skal rendre alt som skal rendres,
		// bare iterer over entities og hent informasjonen du trenger.
	}

	public void tick() {

		handler.tick();
		if (handler.menu.clicked) {
			for (int i = 0; i < 100; i++) {
				addEntity(new Grenade(500, 50, this, rand.nextDouble(),
						rand.nextInt(360)));
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			Entity ent = entities.get(i);
			ent.tick();
			if (ent.removed) {
				removeEntity(ent);
				i--;
				continue;
			}
		}
	}
}