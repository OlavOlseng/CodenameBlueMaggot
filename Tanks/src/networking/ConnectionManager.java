package networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import blueMaggot.GameState;

public class ConnectionManager {

	private ServerSocket listener;
	private Socket client;
	private OutputStream out;
	private InputStream in;
	private ConnectionDelegate delegate;
	private int messageHeader = 6;
	private Thread readThread;
	private Thread writeThread;
	private long sleepTime = 1;
	public DecimalFormat formater;
	
	// private int numPlayers = 2;

	public ConnectionManager(ConnectionDelegate delegate) {
		this.delegate = delegate;
		formater = new DecimalFormat("#00000");
		
	}
	public void endConnection(){
		try{
		if(client != null)
			client.close();
		if(listener != null)
			listener.close();
	}catch (Exception e) {
		// TODO: handle exception
		delegate.connectionFailed(e.getLocalizedMessage());
	}
	}
	public void becomeHost() {
		try {
			listener = new ServerSocket(7227, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			delegate.connectionFailed(e.getLocalizedMessage());
			return;
		}
		TimerTask acceptLoop = new TimerTask() {
			@Override
			public void run() {
				
				try {
					client = listener.accept();
				} catch (IOException e) {
					
					delegate.connectionFailed(e.getLocalizedMessage());
					this.cancel();
					return;
				}
				String terrainMsg = "" + GameState.getInstance().getSelectedLevelBackground().getPath() +"'" + GameState.getInstance().getSelectedLevelTerrain().getPath();
				int len = terrainMsg.length();
				String header = "" + to5DigitString(len);
				try {
					client.getOutputStream().write((header + terrainMsg).getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					delegate.connectionFailed(e.getLocalizedMessage());
					this.cancel();
					return;
				}
				this.cancel();
				init();
				// TODO Auto-generated method stub
			}

		};
		Timer timer = new Timer();
		timer.schedule(acceptLoop, 0, 1000);

	}

	private void init() {

		if (client != null) {
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
			startListenerThread();
			startWritingThread();
			delegate.startOnlineGame();

		}
	}

	public void joinGame(final String adr) {

		client = null;
		TimerTask acceptLoop = new TimerTask() {
			@Override
			public void run() {
				System.out.println("connecting...");
				try {
					client = new Socket(adr, 7227);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					delegate.connectionFailed(e.getLocalizedMessage());
					this.cancel();
					return;
				}
				System.out.println("Connected!");
				
			
				byte[] byteHeader = new byte[5];
				
				
				
				try {
					client.getInputStream().read(byteHeader, 0, 5);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					delegate.connectionFailed(e.getLocalizedMessage());
					this.cancel();
					return;
				}
				int len = Integer.parseInt(new String(byteHeader));
				
				byte[] levelData = new byte[len];
				try {
					client.getInputStream().read(levelData, 0, 5);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					delegate.connectionFailed(e.getLocalizedMessage());
					this.cancel();
					return;
				}
				
				this.cancel();
				init();
				// TODO Auto-generated method stub
			}

		};

		Timer timer = new Timer();
		timer.schedule(acceptLoop, 0, 1000);

	}

	private void startListenerThread() {

		readThread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					int offset = 0;

					byte[] header = new byte[messageHeader];
					int remaining = header.length;
					try {
						synchronized (delegate) {
							if (!delegate.shouldRead()) {
								continue;
							}
						}

						while (remaining > 0) {
							remaining -= in.read(header, offset, header.length - offset);

							offset = header.length - remaining;
						}

						String head = new String(header);
						
						double len = Double.parseDouble((head.substring(1, 6)));
						
						byte[] data = new byte[(int) len];
						offset = 0;
						
						remaining = data.length;

						while(in.available()< len){
						
						}
						while (remaining > 0) {
							remaining -= in.read(data, offset, data.length - offset);
							offset = header.length - remaining;
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

	private void startWritingThread() {
		writeThread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						synchronized (delegate) {
							if (delegate.shouldWrite()) {

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
	private String to5DigitString(double x) {

		if (x >= 0) {
			return formater.format(x);
		} else {
			return "-" + formater.format(Math.abs(x)).substring(1);
		}
	}

}
