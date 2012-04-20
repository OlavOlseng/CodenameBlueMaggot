package level;

import input.InputHandler;

import java.util.ArrayList;
import java.util.List;

import baseGame.BaseGame;
import baseGame.Renderer;

import entity.*;

public class BasicLevel{
	
	private List<Entity> entities;
	private ArrayList<Tank> players;
	private InputHandler handler;
	private BaseGame game;
	
	public BasicLevel(BaseGame game, InputHandler handler){
		this.game = game;
		this.handler = handler;
		
		init();
	}
	public void init(){
		entities = new ArrayList<Entity>();
		
		
		
	}
	
	public void addEntity(Entity ent) {
		if(ent instanceof Tank)
			players.add((Tank)ent);
		entities.add(ent);
	}
	
	public void removeEntity(Entity ent){
		entities.remove(ent);
	}
	
	public void render(Renderer renderer) {
//		Denne skal rendre alt som skal rendres,
//		bare iterer over entities og hent informasjonen du trenger.
	}
	
	public void tick() {
		handler.tick();
		for (Entity ent : entities) {
			ent.tick();
			if(ent.removed){
				removeEntity(ent);
				continue;
			}
		}
	}
}
