package blueMaggot;

import inputhandler.InputHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Networking.ConnectionDelegate;
import Networking.ConnectionManager;
import Networking.NetworkObject;
import Networking.OnlineLevel;
import baseGame.BaseGame;
import baseGame.Rendering.Renderer;
import blueMaggot.maps.cityScape;

import entity.Entity;

public class Game extends BaseGame implements ConnectionDelegate {

	public static int WIDTH = 1024;
	public static int HEIGHT = 768;
	public static Dimension DIMENSION = new Dimension(WIDTH, HEIGHT);

	public static int ALPHA_MASK = -1;

	// network stuff
	public boolean online = false;
	public ArrayList<byte[]> keyStrokes;
	public byte[] keyStrokestoDo;
	public boolean isHost = false;
	private OnlineLevel onlineLevel;
	private ConnectionManager connection;

	public BlueMaggot blueMaggot;
	private InputHandler handler = new InputHandler();

	private cityScape level;

	public static boolean PAUSED = false;

	public Game() {
		handler = new InputHandler();
		addKeyListener(handler);
	}

	public Game(BlueMaggot blueMaggot) {
		this.blueMaggot = blueMaggot;
		blueMaggot.inputReal = handler;
		addKeyListener(handler);
	}

	public void onUpdate(double deltaTime) {
		if (blueMaggot != null)
			blueMaggot.tick();

		deltaTime *= 0.0625;
		if (!PAUSED)
			level.tick(deltaTime);
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

		init(WIDTH, HEIGHT, 60);
		
	}
	
	/* network stuff */
	public void initConnection(boolean isHost) {
		
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
			System.out.println("didRead:" + data.length);
			keyStrokestoDo = data;
		}
	}

	@Override
	public byte[] onWrite() {
		String msg = "";
		List<Entity> objects = onlineLevel.getEntities();
		for (NetworkObject obj : objects) {
			msg += "?" + obj.getObject();
		}
		System.out.println(msg);
		return msg.getBytes();
	}

	@Override
	public boolean shouldRead() {
		return true;
	}

	@Override
	public boolean shouldWrite() {
		return isHost && (onlineLevel != null);
	}

	@Override
	public void startOnlineGame() {
		onlineLevel = new OnlineLevel(this, handler);
	}
}
