package blueMaggot;

import gfx.GameOverlay;
import inputhandler.InputHandler;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import entity.Tank;
import entity.weapon.Gun;

import networking.ConnectionDelegate;
import networking.ConnectionManager;
import networking.NetworkObject;
import networking.OnlineCityScape;

import baseGame.BaseGame;
import baseGame.Rendering.Renderer;
import blueMaggot.maps.cityScape;

public class Game extends BaseGame implements ConnectionDelegate {

	public boolean didTick = false;
	public static int ALPHA_MASK = -1;
	private GameOverlay overlay;
	// network stuff
	public boolean online = false;
	public ArrayList<byte[]> keyStrokes;
	public byte[] keyStrokestoDo;
	private OnlineCityScape onlineLevel;
	private ConnectionManager connection;

	public BlueMaggot blueMaggot;
	private InputHandler handler = new InputHandler();

	public DecimalFormat formater;
	public cityScape level;

	public Game() {
		handler = new InputHandler();
		formater = new DecimalFormat("#00000");

		addKeyListener(handler);
		// DatagramPacket e;
	}

	public Game(BlueMaggot blueMaggot) {
		overlay = new GameOverlay();
		this.blueMaggot = blueMaggot;
		blueMaggot.inputReal = handler;

		formater = new DecimalFormat("#00000");
		addKeyListener(handler);
	}

	@Override
	public void onUpdate(double deltaTime) {

		if (blueMaggot != null)
			blueMaggot.tick();

		handler.tick(deltaTime);
		deltaTime *= 0.0625;

		if (!GameState.getInstance().isPaused()) {// ||
													// !GameState.getInstance().isRunning())
													// {
			level.tick(deltaTime);
			didTick = true;
		}

	}

	@Override
	public void onDraw(Renderer renderer) {

		renderer.clearAllPixelData(Color.WHITE.getRGB());
		level.onDraw(renderer);

	}

	@Override
	public void onUppdateUI(Renderer renderer) {
		GameState state = GameState.getInstance();
		if (level != null && state.getPlayers().size() > 0)
			overlay.paintOverlay(renderer.getGraphics());
	}

	// public byte[] parseKeyStrokes() {
	// byte[] msg = new byte[7];
	// msg[0] = handler.left2.toByte();
	// msg[1] = handler.up2.toByte();
	// msg[2] = handler.right2.toByte();
	// msg[3] = handler.down2.toByte();
	// msg[4] = handler.rotateL2.toByte();
	// msg[5] = handler.rotateR2.toByte();
	// msg[6] = handler.fire2.toByte();
	// return msg;
	// }

	public void startReuglarGame() {
		System.out.println("starting level: " + GameState.getInstance().getSelectedLevelBackground().getName().split("_")[0]);
		level = new cityScape(this, handler);
		level.init();
		init(GameState.getInstance().getWidth(), GameState.getInstance().getHeight(), 60);
		GameState.getInstance().setRunning(true);
	}

	/* network stuff */
	public void initConnection(boolean isHost, String addr) {
		System.out.println("initiating connection");
		connection = new ConnectionManager(this);
		if (isHost) {
			GameState.getInstance().setPlayerNumber(2);
			connection.becomeHost();
			GameState.getInstance().setHost(true);
		} else {
			GameState.getInstance().setPlayerNumber(1);
			connection.joinGame(addr);
			GameState.getInstance().setHost(false);
		}
	}

	@Override
	public void connectionFailed(String message) {
		System.out.println("connection failed: " + message);
	}

	@Override
	public void readData(byte[] data) {

		String gameData = new String(data);
		String[] parts = gameData.split("\\@");
	
		String gameState = parts[0];
		String[] properties = gameState.split("\\'");
//		System.out.println("gamestate: " + gameState);
		Tank player1;
		Tank player2 ;
	
		if(!GameState.getInstance().isHost()&& level != null &&level.getPlayers().size()> 1){
			int score1 = Integer.parseInt(properties[0]);

			int score2= Integer.parseInt(properties[1]);
			int life1= Integer.parseInt(properties[2]);
			int life2= Integer.parseInt(properties[3]);	
			Gun gun1 = Gun.valueOf(properties[4]);
			Gun gun2 = Gun.valueOf(properties[5]);
			
		Tank temp = GameState.getInstance().players.get(0);
		if(temp.getPlayerNumber() == 1){
			player1 = temp;
			player2 = GameState.getInstance().players.get(1);
		}else{
			player2 = temp;
			player1 = GameState.getInstance().players.get(1);
		}
		
		player1.setScore(score1);
		player1.setLife(life1);
		player1.setCurrentWeapon(gun1);
		
		player2.setCurrentWeapon(gun2);
		player2.setScore(score2);
		player2.setLife(life2);
		
		GameState state = GameState.getInstance();
		
		state.setPlayers(level.getPlayers());
		
		}
		else{
			if(GameState.getInstance().isHost()){
			Gun gun1 = Gun.valueOf(properties[4]);
			if(level != null & level.getPlayers() != null)
			level.getPlayers().get(0).setCurrentWeapon(gun1);
			}
			
		}
		if (data.length > 0) {

			onlineLevel.catchResponse(parts[1]);

		}
		
	}

	@Override
	public byte[] onWrite() {

		GameState state = GameState.getInstance();

		String gameState ="";
		if(state.getPlayers() != null)
			gameState= state.getPlayers().get(0).getScore() + "'"+ state.getPlayers().get(1).getLife()+ "'" + state.getPlayers().get(0).getScore()+ "'" + state.players.get(1).getLife()+"'" +state.players.get(0).getCurrentWeaponName()+"'" +state.players.get(1).getCurrentWeaponName() ;
		
		gameState += "@";


		String msgBody = "";
		
		List<NetworkObject> objects = onlineLevel.getNetworkObjectList();
		List<Integer> deadKeys = new ArrayList<Integer>();

		for (int i = 0; i < objects.size(); i++) {

			NetworkObject obj = objects.get(i);
			if (obj.shouldBeSent()) {
				String objectString = obj.getObject();

				if (objectString != null && !objectString.equals(""))
					;
				msgBody += "?" + objectString;

				if (obj.isRemoved()) {
					deadKeys.add(obj.getId());
					onlineLevel.getNetworkObjectList().remove(i);
					i--;
				}
			}

		}
		for (Integer key : deadKeys) {
			onlineLevel.getNetworkObjects().remove(key);

		}


		String msgHeader = "1" + to5DigitString(msgBody.length() + gameState.length()) ;


		return (msgHeader + gameState + msgBody).getBytes();

	}

	private String to5DigitString(double x) {

		if (x >= 0) {
			return formater.format(x);
		} else {
			return "-" + formater.format(Math.abs(x)).substring(1);
		}
	}

	@Override
	public boolean shouldRead() {

		return onlineLevel != null;

	}

	@Override
	public boolean shouldWrite() {
		boolean temp = didTick;
		didTick = false;

		return (onlineLevel != null) && temp && onlineLevel.getNetworkObjects().size() > 0;

	}

	@Override
	public void startOnlineGame() {
		onlineLevel = new OnlineCityScape(this, handler);
		onlineLevel.init();
		level = onlineLevel;
		init(GameState.getInstance().getWidth(), GameState.getInstance().getHeight(), 60);
		GameState.getInstance().setRunning(true);
		GameState.getInstance().players = level.getPlayers();
		
	}
}
