package baseGame;

import inputhandler.InputHandler;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import level.BasicLevel;
import level.Terrain;
import level.testLevel;

import Networking.ConnectionDelegate;
import Networking.ConnectionManager;
import Networking.NetworkObject;
import Networking.OnlineLevel;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;

import entity.Entity;
import entity.Tank;

public class testGame extends BaseGame implements ConnectionDelegate {

	public static int WIDTH = 1024;
	public static int HEIGHT = 720;
	public static int ALPHA_MASK = -1;
	public boolean online = false;
	public ArrayList<byte[]> keyStrokes;
	public byte[] keyStrokestoDo;
	public boolean isHost = false;
	
	private InputHandler handler = new InputHandler();
	
	private testLevel level ;
	private OnlineLevel onlineLevel;
	private ConnectionManager connection;
	
	public void init(){

		//startReularGame();
		startOnlineGame(false);
		//startOnlineGame(true);
		
	}
	
	public testGame() {
	
		addKeyListener(handler);
		setBackgroundColor(Color.BLACK);
		Random random = new Random();
		setBackgroundColor(Color.BLACK);	
		connection = new ConnectionManager(this);
		
		
		
	}

	public void onUpdate(long deltaTime) {
			
		level.tick();
		
	}
	
	@Override
	public void onDraw(Renderer renderer) {
		renderer.clearAllPixelData(Color.WHITE.getRGB());
		level.onDraw(renderer);
		// renderer.makeTransparent(ALPHA_MASK);

	}
	public byte[] parseKeyStrokes(){
	
		byte[] msg = new byte[7];
		msg[0] = handler.left2.toByte();
		msg[1] = handler.up2.toByte();
		msg[2] = handler.right2.toByte();
		msg[3] = handler.down2.toByte();
		msg[4] = handler.rotateL2.toByte();
		msg[5] = handler.rotateR2.toByte();
		msg[6] = handler.fire2.toByte();
		return msg;
		
	}
	

	
	public void startReularGame() {
		// TODO Auto-generated method stub
	
		online = true;
		level =  new testLevel(this,handler);
		init(WIDTH,HEIGHT,60);
		
		
	}
	public void startOnlineGame(boolean isHost){
		
			if(isHost){
				connection.becomeHost();
				this.isHost = true;
			}else{
				connection.joinGame("127.0.0.1");
				this.isHost = false;
			}
			
	}

	@Override
	public void connectionFailed(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}
	@Override
	
	public void readData(byte[] data) {
		// TODO Auto-generated method stub
		
		if(data.length >0){
			System.out.println("didRead:" + data.length);
			
			keyStrokestoDo = data;
		}
	
	
	}
	
	@Override
	public byte[] onWrite() {
		// TODO Auto-generated method stub
				
		String msg = "";
		List<Entity> objects = onlineLevel.getEntities();
		for(NetworkObject obj:objects){
			msg += "?"+ obj.getObject();
		}	
		System.out.println(msg);
		return msg.getBytes();
	}
	@Override
	public boolean shouldRead() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean shouldWrite() {
		// TODO Auto-generated method stub
		return   isHost && (onlineLevel != null);
	}
	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
		onlineLevel = new OnlineLevel(this, handler);
		
	}

	

}
