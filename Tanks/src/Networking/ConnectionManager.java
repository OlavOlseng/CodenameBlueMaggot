package Networking;

import input.InputHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import level.BasicLevel;

import baseGame.BaseGame;

public class ConnectionManager {

	private ServerSocket listener;
	private Socket client;
	private OutputStream out;
	private InputStream in;
	private ConnectionDelegate delegate;
	private int messageLenght = 7;
	private Thread readThread;
	private Thread writeThread;

	//private int numPlayers = 2;
	
	
	public ConnectionManager(ConnectionDelegate delegate){
	
		this.delegate = delegate;
		
		
	}
	
	
	
	public void becomeHost(){
		try {
			listener = new ServerSocket(7227,2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			delegate.connectionFailed(e.getLocalizedMessage());
			return;
		}
		
		TimerTask acceptLoop = new TimerTask(){
			@Override
			public void run() {
				System.out.println("hay");
				try {
					client = listener.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("fack");
					delegate.connectionFailed(e.getLocalizedMessage());
					this.cancel();
					return;
				}
				System.out.println("hay");
				this.cancel();
				init();
				// TODO Auto-generated method stub
			}
			 
		};
		Timer timer = new Timer();
		timer.schedule(acceptLoop,0,1000);
		
	}
	private void init(){
	
	if(client !=null){
	try {
		out = client.getOutputStream();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		delegate.connectionFailed(e.getLocalizedMessage());
		return;
	}
	try {
		in = client.getInputStream();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		delegate.connectionFailed(e.getLocalizedMessage());
		return;
	}
	System.out.println("lidshfu");
	startListenerThread();
	startWritingThread();
	delegate.startGame();

	}}
	public void joinGame(final String adr){
		
			client = null;
			TimerTask acceptLoop = new TimerTask(){
				@Override
				public void run() {
					System.out.println("connecting...");
					try {
						client = new Socket(adr,7227);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						delegate.connectionFailed(e.getLocalizedMessage());
						this.cancel();
						return;
					}
					System.out.println("Connected!");
					
					this.cancel();
					init();
					// TODO Auto-generated method stub
				}
				 
			};
			Timer timer = new Timer();
			timer.schedule(acceptLoop,0,1000);
			
		
	}
	private void startListenerThread(){
		readThread = new Thread(){
			public void run(){
				while(true){
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int offset = 0;
					
					byte[] data = new byte[messageLenght];
					int remaining = data.length;
					try {
						synchronized (delegate) {
							if(!delegate.shouldRead()){
								continue;
							}
						}
						while(remaining >0){
							remaining-=in.read(data,offset,data.length-offset);
						}
						synchronized (delegate) {
							delegate.readData(data);
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						synchronized (delegate) {
						delegate.connectionFailed(e.getLocalizedMessage());
						
						}
						return;
					}
					
					
					
				}
			}
		};
		readThread.start();
		
	}
	private void startWritingThread(){
		writeThread = new Thread(){
			public void run(){
				while(true){
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					synchronized (delegate) {
						if(delegate.shouldWrite()){
						out.write(delegate.onWrite());
						}
						}
					}
					
				 catch (IOException e) {
					// TODO Auto-generated catch block
					
					synchronized (delegate) {
						delegate.connectionFailed(e.getLocalizedMessage());
					}
					return;
				}
				
				}
			}
		};
		writeThread.start();
	}
	
}
