package networking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import entity.AirStrikeBeacon;
import entity.BubbleHearth;
import entity.Bullet;
import entity.Entity;
import entity.FloatingPoint;
import entity.Grenade;
import entity.Mine;
import entity.Package;
import entity.Rocket;
import entity.ScoreBubble;
import entity.Shell;
import inputhandler.InputHandler;
import baseGame.BaseGame;
import blueMaggot.GameState;
import blueMaggot.maps.cityScape;

public class OnlineCityScape extends cityScape {
	private int objectCount;
	private int multiple = 2;
		
	private HashMap<Integer, NetworkObject> networkObjects;
	private ArrayList<NetworkObject> networkObjectList;
	private ArrayList<String[]> movementsToDo;
	private boolean readyForLan;
	private long lastTime;
	private boolean isClient;
	private OnlineTank player1;

	public OnlineCityScape(BaseGame game, InputHandler handler) {
		super(game, handler);

		if (GameState.getInstance().getPlayerNumber() != 2) {
			isClient = true;
			System.out.println("true");
			objectCount++;
			
		}

	}

	@Override
	public void init() {
		networkObjectList = new ArrayList<NetworkObject>();
		networkObjects = new HashMap<Integer, NetworkObject>();
		movementsToDo = new ArrayList<String[]>();
		lastTime = System.currentTimeMillis();
		super.init();
		readyForLan = true;

	}

	@Override
	public boolean shouldSpawnCrate() {

		return super.shouldSpawnCrate() && !isClient && readyForLan;

	}

	@Override
	public boolean shouldSpawnBubbleHearth() {
		
		return super.shouldSpawnBubbleHearth() && !isClient && readyForLan;
	}

	@Override
	public void addPlayers() {
		if (!isClient) {

			Random rand = new Random();

			OnlineTank player2;

			player1 = new OnlineTank(playerSpawns.getPoint(), 1, handler, this, null);
			player2 = new OnlineTank(playerSpawns.getPoint(), 2, handler, this, null);

			
			addEntity(player1, objectCount, true, true);

			addEntity(player2, objectCount, true, true);

		}

	}

	@Override
	public void checkGameOver() {
		if(!isClient){
			super.checkGameOver();
		}
	}
	@Override
	public void addEntity(Entity entity) {
		if (entity.getNetworkObjectType() != NetworkObjectType.NO_SYNC) {
			networkObjects.put(new Integer(objectCount), entity);
			networkObjectList.add(entity);
			entity.setShouldBeSent(true);
			entity.setId(objectCount);
			objectCount+= multiple;

		}

		super.addEntity(entity);

	}

	public void addEntity(Entity ent, Integer id, boolean doSend, boolean doTick) {
		networkObjectList.add(ent);
		ent.setShouldBeSent(doSend);
		ent.setId(id);
		ent.setSouldTick(doTick);
		networkObjects.put(id, ent);
		objectCount += multiple;
		super.addEntity(ent);
	}

	public ArrayList<NetworkObject> getNetworkObjectList() {
		return networkObjectList;
	}

	public void catchResponse(String entityData) {
		String[] objects = entityData.split("\\?");
		
		for (int i = 1; i < objects.length; i++) {
			String[] properties = objects[i].split("\\'");
			synchronized (movementsToDo) {


				movementsToDo.add(properties);

			}

		}

	}

	public HashMap<Integer, NetworkObject> getNetworkObjects() {
		return networkObjects;
	}

	@Override
	protected boolean shouldSpawnBubble() {
		if (isClient)
			return false;
		else
			return super.shouldSpawnBubble();
	}

	@Override
	public void tick(double dt) {

		NetworkObject obj;
		double time;
		
		synchronized (movementsToDo) {
			if (movementsToDo.size() > 0 && readyForLan) {
				time = (System.currentTimeMillis() - lastTime) * 0.0625;
			
				

				for (int i = 0; i < movementsToDo.size(); i++) {

					
					String[] move = movementsToDo.get(i);
					
					int id = (int) Double.parseDouble(move[1]);
					int type = (int) Double.parseDouble(move[2]);
					
					if ((obj = networkObjects.get(id)) != null) {
						if(!obj.getNetworkObjectType().equals(type)){
							System.out.println("Overlap");
						}
						obj.handleMessage(move);
						if (obj.isRemoved()) {
							networkObjects.remove(obj);
							networkObjectList.remove(obj);
							entities.remove(obj);

						}

					} else {
						NetworkObject ent = null;

						boolean doSend = false;
						boolean doTick = false;
					

						if (NetworkObjectType.TANK.equals(type) && GameState.getInstance().getPlayerNumber()!=2) {
							ent = new OnlineTank(0, 0, 0, handler, this, move);
							System.out.println("new tank!");
							if (Integer.parseInt(move[7]) == GameState.getInstance().getPlayerNumber()) {
								player1 = (OnlineTank) ent;
								doSend = true;
							
								
							}

						} else if (NetworkObjectType.SHELL.equals(type))
							ent = new Shell(0, 0, this, 0, 0);

						else if (NetworkObjectType.SCORE_BUBBLE.equals(type))
							ent = new ScoreBubble(new FloatingPoint(0.0, 0.0), this, 0, 0, 0);
						else if (NetworkObjectType.GRENADE.equals(type))
							ent = new Grenade(0, 0, this, 0, 0);
						else if (NetworkObjectType.ROCKET.equals(type))
							ent = new Rocket(0, 0, this, 0, 0);

						else if (NetworkObjectType.MINE.equals(type))
							ent = new Mine(0, 0, this, 0);
						else if (NetworkObjectType.PACKAGE.equals(type))
							ent = new Package(new FloatingPoint(0.0, 0.0), this);
						else if (NetworkObjectType.BUBBLE_HEARTH.equals(type))
							ent = new BubbleHearth(new FloatingPoint(0, 0), this);
						else if (NetworkObjectType.AIR_STRIKE.equals(type))
							ent = new AirStrikeBeacon(0, 0, this, 0, 0);
						else if (NetworkObjectType.BULLET.equals(type))
							ent = new Bullet(0, 0, this, 0);

						if (ent != null) {
							ent.handleMessage(move);

							addEntity((Entity) ent, id, doSend, doTick);

						}
					}
				}

				movementsToDo = new ArrayList<String[]>();
				lastTime = System.currentTimeMillis();
				
			}
			
			if (isClient && player1 != null)
				player1.tick(dt);
			
			super.tick(dt);
			

		}

	}
}
