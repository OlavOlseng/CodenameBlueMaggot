package blueMaggot;

import inputhandler.InputHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import Networking.ConnectionDelegate;
import Networking.ConnectionManager;
import Networking.NetworkObject;
import Networking.OnlineCityScape;

import baseGame.BaseGame;
import baseGame.Rendering.Renderer;
import blueMaggot.maps.cityScape;

import entity.Entity;

public class Game extends BaseGame implements ConnectionDelegate {

	public static int WIDTH = 1024;
	public static int HEIGHT = 768;
	public static Dimension DIMENSION = new Dimension(WIDTH, HEIGHT);
	public boolean didTick = false;
	public static int ALPHA_MASK = -1;
	public static boolean PAUSED = false;
	
	// network stuff
	public boolean online = false;
	public ArrayList<byte[]> keyStrokes;
	public byte[] keyStrokestoDo;
	private OnlineCityScape onlineLevel;
	private ConnectionManager connection;

	public BlueMaggot blueMaggot;
	private InputHandler handler = new InputHandler();

	private cityScape level;

	// customizable player variables!
	public String hostIp;
	public String nickPlayerOne;
	public String nickPlayerTwo;
	public boolean isHost = false;


	public Game() {
		handler = new InputHandler();
		addKeyListener(handler);
	}

	public Game(BlueMaggot blueMaggot) {
		this.blueMaggot = blueMaggot;
		blueMaggot.inputReal = handler;
		addKeyListener(handler);
	}

	@Override
	public void onUpdate(double deltaTime) {
		if (blueMaggot != null)
			blueMaggot.tick();

		handler.tick(deltaTime);
		deltaTime *= 0.0625;
		if (!PAUSED) {
			level.tick(deltaTime);
			didTick = true;
		}
	}

	@Override
	public void onDraw(Renderer renderer) {
		renderer.clearAllPixelData(Color.WHITE.getRGB());
		level.onDraw(renderer);
		
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
		level = new cityScape(this, handler);
		level.init();
		init(WIDTH, HEIGHT, 60);

	}

	/* network stuff */
	public void initConnection(boolean isHost) {
		connection = new ConnectionManager(this);
		if (isHost) {
			connection.becomeHost();
			this.isHost = true;
		} else {
			connection.joinGame("127.0.0.1");
			this.isHost = false;
		}
	}

	@Override
	public void connectionFailed(String message) {
		System.out.println(message);
	}

	@Override
	public void readData(byte[] data) {
		if (data.length > 0) {
			onlineLevel.catchResponse(new String(data));

		}
	}

	@Override
	public byte[] onWrite() {
		String msgBody = "";

		List<Entity> objects = onlineLevel.getEntities();
		for (int i = 0; i < objects.size(); i++) {
			NetworkObject obj = objects.get(i);
			msgBody += "?" + obj.getObject();
		}

		String msgHeader = "1" + to5DigitString(msgBody.length());

		return (msgHeader + msgBody).getBytes();
	}

	private String to5DigitString(double x) {
		String part1 = String.format("%.0f", x);
		String part2 = String.format("%." + (5 - part1.length()) + "f", x - (int) x).substring(1);
		return part1 + part2;
	}

	@Override
	public boolean shouldRead() {
		return !isHost;
	}

	@Override
	public boolean shouldWrite() {
		boolean temp = didTick;
		didTick = false;
		return isHost && (onlineLevel != null) && temp;
	}

	@Override
	public void startOnlineGame() {
		onlineLevel = new OnlineCityScape(this, handler);
		onlineLevel.init();
		level = onlineLevel;
		init(WIDTH, HEIGHT, 60);
	}
}
