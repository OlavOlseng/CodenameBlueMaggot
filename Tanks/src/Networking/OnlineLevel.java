package Networking;

import entity.Entity;
import entity.Tank;
import inputhandler.InputHandler;
import baseGame.BaseGame;
import blueMaggot.maps.cityScape;

public class OnlineLevel extends cityScape {
	private int objectCount = 0;

	public OnlineLevel(BaseGame game, InputHandler handler) {
		super(game, handler);

	}

	@Override
	public void addEntity(Entity entity) {
		super.addEntity(entity);
	}

	public void setNetworkValues(Entity entity) {
		entity.setId(objectCount);
		objectCount++;
	}

}
