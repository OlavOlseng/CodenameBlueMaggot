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
import java.util.Random;

import javax.imageio.ImageIO;

import level.BasicLevel;
import level.Terrain;
import level.testLevel;

import Networking.ConnectionDelegate;
import Networking.ConnectionManager;
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
	public boolean isHost = false;
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice gs = ge.getDefaultScreenDevice();
	InputHandler handler = new InputHandler();
	testLevel level ;
	ConnectionManager connection;
	
	public void init(){
		keyStrokes = new ArrayList<byte[]>();
		//connection.becomeHost();
		//isHost = true;
		connection.joinGame("127.0.0.1");
		
		//startGame();
		
	}
	public testGame() {
		
		addKeyListener(handler);
		setBackgroundColor(Color.BLACK);
		Random random = new Random();
		setBackgroundColor(Color.BLACK);	
		connection = new ConnectionManager(this);
		
		
		
	}

	public void onUpdate(long deltaTime) {
		if(isHost){
		keyStrokes.add(parseKeyStrokes());
		}
		level.tick();
		
		
		

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
	@Override
	public void onDraw(Renderer renderer) {
		renderer.clearAllPixelData(Color.WHITE.getRGB());
		level.onDraw(renderer);
		// renderer.makeTransparent(ALPHA_MASK);

	}

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		System.out.println("started");
		online = true;
		level =  new testLevel(this, handler);
		init(WIDTH,HEIGHT,60);
		
		
	}

	@Override
	public void connectionFailed(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}
	@Override
	public void readData(byte[] data) {
		// TODO Auto-generated method stub
	if(!isHost){
		
		if(data[0] == 0x1){
			handler.left2.toggle(true);
			}
		else{
			handler.left2.toggle(false);
		}
		if(data[1] == 0x1){
			handler.up2.toggle(true);
		}
		else{
			handler.up2.toggle(false);
		}
		if(data[2] == 0x1){
			handler.right2.toggle(true);
		}
		else{
			handler.right2.toggle(false);
		}
		
	}/*else{
		if(data[0] == 0x1){
			handler.left1.toggle(true);
			}
		else{
			handler.left1.toggle(false);
		}
		if(data[1] == 0x1){
			handler.up1.toggle(true);
		}
		else{
			handler.up1.toggle(false);
		}
		if(data[2] == 0x1){
			handler.right1.toggle(true);
		}
		else{
			handler.right1.toggle(false);
		}
	}*/
	}
	@Override
	public byte[] onWrite() {
		// TODO Auto-generated method stub
		byte[] msg = new byte[keyStrokes.size()*7];
		
		
		for(int i = 0;i<keyStrokes.size();i++){
			for(int ii = 0;ii<7;ii++){
				msg[ii +i*7] = keyStrokes.get(i)[ii];
			}
		}
		System.out.println(new String( msg));
		keyStrokes = new ArrayList<byte[]>();
		return msg;
	}
	@Override
	public boolean shouldRead() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean shouldWrite() {
		// TODO Auto-generated method stub
		return isHost;
	}

	

}
