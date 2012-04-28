package networking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import entity.AirStrikeBeacon;
import entity.BubbleHearth;
import entity.Entity;
import entity.FloatingPoint;
import entity.Grenade;
import entity.Mine;
import entity.Package;
import entity.Rocket;
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
	private ArrayList<String[]> movementsToDo;
	

	

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
		movementsToDo = new ArrayList<String[]>();
		lastTime = System.currentTimeMillis();
		super.init();
	
	}
	@Override
	public boolean shouldSpawnCrate(){
		return !isClient;
	}
	@Override
	public boolean shouldSpawnBubbleHearth(){
		return !isClient;
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
			synchronized (movementsToDo) {
				movementsToDo.add(properties);
				
			}
		
			
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
			
			for(int i = 0;i<movementsToDo.size();i++){

			String[] move = movementsToDo.get(i);
			
			int id = (int)Double.parseDouble(move[1]);
			int type  = (int)Double.parseDouble(move[2]);
			
			if((obj = networkObjects.get(id)) != null){
				obj.handleMessage(move);
				if(obj.isRemoved()){
					
					networkObjects.remove(obj);
					entities.remove(obj);
				
				}
					
				}else{
					NetworkObject ent = null;
					if(NetworkObjectType.TANK.equals(type))
						return;
					else
						if(NetworkObjectType.SHELL.equals(type))
							 ent = new Shell(0, 0, this, 0, 0);
							
						else
							if(NetworkObjectType.SCORE_BUBBLE.equals(type))
								ent  = new ScoreBubble(new FloatingPoint(0.0,0.0),this,0,0,0);
							else
								if(NetworkObjectType.GRENADE.equals(type))
									ent  = new Grenade(0,0,this,0,0);
								else
									if(NetworkObjectType.ROCKET.equals(type))
										ent = new Rocket(0, 0, this, 0, 0);
							
									else
										if(NetworkObjectType.MINE.equals(type))
											ent = new Mine(0,0,this,0);
										else
											if(NetworkObjectType.PACKAGE.equals(type))
												ent = new Package(new FloatingPoint(0.0, 0.0), this);
											else
												if(NetworkObjectType.BUBBLE_HEARTH.equals(type))
													ent = new BubbleHearth(new FloatingPoint(0, 0), this);
												else
													if(NetworkObjectType.AIR_STRIKE.equals(type))
														ent = new AirStrikeBeacon(0, 0, this, 0, 0);
												
				if(ent != null){		
					ent.handleMessage(move);
					addEntity((Entity)ent, id);
					}
				}

				

				
			}
			super.tick(time);
			movementsToDo = new ArrayList<String[]>();
			lastTime = System.currentTimeMillis();
			return;
		}

		super.tick(dt);

	}

	}
	}
