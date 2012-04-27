package Networking;

import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import java.util.concurrent.locks.ReentrantLock;
import entity.Package;
import level.BasicLevel;

import entity.Entity;
import entity.Explosion;
import entity.FloatingPoint;
import entity.ScoreBubble;
import entity.Shell;
import entity.Tank;
import inputhandler.InputHandler;
import baseGame.BaseGame;
import baseGame.derp;
import blueMaggot.maps.cityScape;

public class OnlineCityScape extends cityScape {
	private int objectCount = 0;

	private HashMap<Integer,NetworkObject> networkObjects;
	private Stack<String[]> movementsToDo;

	

	private long lastTime;
	private boolean isClient;

	public OnlineCityScape(BaseGame game, InputHandler handler) {

		super(game, handler);

		if(derp.playerNumber != 1)
			isClient = true;


	}

	@Override

	public void init(){
		
		networkObjects= new HashMap<Integer, NetworkObject>();
		movementsToDo = new Stack<String[]>();
		lastTime = System.currentTimeMillis();

	
	}
	@Override

	public void addPlayers(){
		Random rand = new Random();
		
		Tank player1 = new Tank(playerSpawns.get(rand.nextInt(playerSpawns.size())), 1, handler, this);
		Tank player2 = new Tank(playerSpawns.get(rand.nextInt(playerSpawns.size())), 2, handler, this);
		player1.setIsOnlineGameClient(isClient);
		player2.setIsOnlineGameClient(isClient);
		
		addEntity(player1);
		addEntity(player2);
		
	}
	
	@Override
	public void addEntity(Entity entity) {
		if(entity.getNetworkObjectType() != NetworkObjectType.NO_SYNC){
		networkObjects.put(new Integer(objectCount), entity);


		networkObjects.put(new Integer(objectCount), entity);


		entity.setId(objectCount);
		objectCount++;
		}
		
		super.addEntity(entity);

		}

public void addEntity(Entity ent,Integer id){
	ent.setIsOnlineGameClient(true);
	networkObjects.put(id, ent);
	objectCount++;
	super.addEntity(ent);
	}

	public void catchResponse(String entityData){
		
		String[] objects = entityData.split("\\?");
		
		for (int i = 1;i<objects.length;i++){
			String[] properties = objects[i].split("\\'");	
			movementsToDo.add(properties);
			
	}
	}

	

	public HashMap<Integer,NetworkObject> getNetworkObjects(){
		return networkObjects;
	}
	@Override 
	protected boolean shouldSpawnBubble() {
		if(isClient)
			return false;
		else
		return super.shouldSpawnBubble();
	}



	@Override
	public void tick(double dt) {
		NetworkObject obj;
		double time;

		
		synchronized (movementsToDo) {	
			if(movementsToDo.size()>0){
				time = ( System.currentTimeMillis() -lastTime)*0.0625;
			
				dt = time;
			
			while(movementsToDo.size()>0){
				
			String[] move = movementsToDo.pop();
			
			
			int id = (int)Double.parseDouble(move[1]);
			int type  = (int)Double.parseDouble(move[2]);
		
		
			
			if((obj = networkObjects.get(id)) != null){
				
				boolean didDie = Boolean.parseBoolean(move[3]);
				obj.handleMessage(move);
				if(didDie){
					
					networkObjects.remove(obj);
					entities.remove(obj);
					obj.remove();
				}
					
				}else{
					NetworkObject ent = null;
					if(NetworkObjectType.TANK.equals(type))
						return;
					else
						if(NetworkObjectType.SHELL.equals(type))
							 ent = new Shell(0, 0, this, 0, 0);
							
							

					/*else
						if(NetworkObjectType.SCORE_BUBBLE.equals(type))
							ent = new ScoreBubble(new FloatingPoint(0, 0), 4,this, 0.5, 0, 0);
								
						else
							if(NetworkObjectType.EXPLOSION.equals(type))
								ent = new Explosion(0,0,0,this,0);
							else
								if(NetworkObjectType.PACKAGE.equals(type))
									ent = new Package(new FloatingPoint(0, 0),(BasicLevel)this);*/
						
					
					if(ent != null){
					System.out.println("new");
					ent.handleMessage(move);
					addEntity((Entity)ent, id);
					

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
	}
