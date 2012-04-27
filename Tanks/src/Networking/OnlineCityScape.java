package Networking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

import level.BasicLevel;

import entity.Entity;
import entity.Shell;
import entity.Tank;
import inputhandler.InputHandler;
import baseGame.BaseGame;
import blueMaggot.maps.cityScape;

public class OnlineCityScape extends  cityScape{
	private int objectCount = 0;
	private HashMap<Integer,NetworkObject> networkObjects;
	private Stack<double[]> movementsToDo;
	private long lastTime;

	public OnlineCityScape(BaseGame game, InputHandler handler) {
	
		super(game, handler);
	

	}

	@Override
	public void init(){
		networkObjects= new HashMap<Integer, NetworkObject>();
		movementsToDo = new Stack<double[]>();
		lastTime = System.currentTimeMillis();
		
		super.init();
	}
	@Override
	public void addEntity(Entity entity) {
		
		
		
		
		networkObjects.put(new Integer(objectCount), entity);
		
		entity.setId(objectCount);
		objectCount++;
		super.addEntity(entity);
		
	}

	

	public void catchResponse(String entityData){
		System.out.println(entityData);
		String[] objects = entityData.split("\\?");
		
		for (int i = 1;i<objects.length;i++){
			String[] properties = objects[i].split("\\'");
			
			double[] instructions = new double[6];
			 //id
			instructions[0] = Double.parseDouble(properties[1]);
			//type
			instructions[1] = Double.parseDouble(properties[2]);
			//x
			instructions[2] = Double.parseDouble(properties[3]);
			//y
			instructions[3] = Double.parseDouble(properties[4]);
			//dx
			instructions[4] = Double.parseDouble(properties[5]);
			//dy
			instructions[5] = Double.parseDouble(properties[6]);
			
				
			synchronized (movementsToDo) {
				movementsToDo.add(instructions);
			}
			
		}
		
		
	}

	
	@Override
	public void tick(double dt){
		NetworkObject obj;
		double time;
		synchronized (movementsToDo) {
			
			if(movementsToDo.size()>0){
				time = ( System.currentTimeMillis() -lastTime)*0.0625;
			
				dt = time;
			while(movementsToDo.size()>0){
	
			double[] move = movementsToDo.pop();
			
			int type = (int)move[1];
			double x = move[2];
			double y = move[3];
			double dx = move[4];
			double dy = move[5];
			
			if((obj = networkObjects.get((int)move[0])) != null){
				obj.setLocation(x , y);
				obj.setSpeed(dx, dy);
				obj.setIsOnlineGameClient(true);
				
				}else{
					if(NetworkObjectType.TANK.equals(type)){
						
					}else{
						if(NetworkObjectType.SHELL.equals(type)){
							NetworkObject ent = new Shell(x, y, this, 0, 0);
							addEntity((Entity)ent);
							networkObjects.put(objectCount, ent);
							ent.setId(objectCount);
							objectCount++;
							
							ent.setIsOnlineGameClient(true);
							
						}
					}
				}
		}
			
			
			super.tick(time);
			
			lastTime = System.currentTimeMillis();

			return;
			}
		}
	
		super.tick(dt);
	
	}
	


	

}
