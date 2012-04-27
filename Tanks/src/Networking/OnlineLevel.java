package Networking;

import java.util.HashMap;

import entity.Entity;
import entity.Shell;
import entity.Tank;
import inputhandler.InputHandler;
import baseGame.BaseGame;
import blueMaggot.maps.cityScape;

public class OnlineLevel extends cityScape {
	private int objectCount = 0;
	private HashMap<Integer,NetworkObject> networkObjects;
	public OnlineLevel(BaseGame game, InputHandler handler) {
	
		super(game, handler);
		

	}

	@Override
	public void addEntity(Entity entity) {
		
		if(networkObjects == null){
			networkObjects= new HashMap<Integer, NetworkObject>();
		}
		entity.setId(objectCount);
		System.out.println("here");
		networkObjects.put(new Integer(objectCount), entity);
		System.out.println(networkObjects.keySet());
		objectCount++;
		super.addEntity(entity);
		
	}

	

	public void catchResponse(String entityData){
		String[] objects = entityData.split("\\?");
		
		for (int i = 1;i<objects.length;i++){
			
			String[] properties = objects[i].split("\\'");
			int id = (int)Double.parseDouble(properties[1]);
			
			double x = Double.parseDouble(properties[2]);
			double y = Double.parseDouble(properties[3]);
			double dx = Double.parseDouble(properties[4]);
			double dy = Double.parseDouble(properties[5]);
			NetworkObject obj;
			if( (obj = networkObjects.get(id)) != null){
			obj.setLocation(x, y);
			obj.setSpeed(dx, dy);
			}
			
		}
		
	}
	public void setNetworkValues(Entity entity) {
		entity.setId(objectCount);
		objectCount++;
	}

}
