package Networking;

import entity.Entity;
import entity.Tank;
import inputhandler.InputHandler;
import baseGame.BaseGame;
import level.testLevel;

public class OnlineLevel extends testLevel {
	private int objectCount = 0;
	
	public OnlineLevel(BaseGame game, InputHandler handler) {
		super(game, handler);
		
		// TODO Auto-generated constructor stub
		
	}
	@Override
	public void addEntity(Entity entity){
		
		super.addEntity(entity);
		
	}
	public	void setNetworkValues(Entity entity){
		entity.setId(objectCount);
		objectCount++;
	}

}
