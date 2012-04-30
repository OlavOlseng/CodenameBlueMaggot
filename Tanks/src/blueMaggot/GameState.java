package blueMaggot;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import entity.Tank;

public class GameState {

	private boolean paused = false;
	private boolean gameOver = false;
	private boolean running = false;
	private int playerNumber;

	public boolean isHost = false;
	public File selectedLevelTerrain = new File("./lvl/Cityscape_terrain.png");
	public File selectedLevelBackground = new File("./lvl/Cityscape2_background.png");

	public int width = 1280;
	public int height = 720;
	public Dimension dimension = new Dimension(width, height);

	// customizable player variables!
	public ArrayList<Tank> players = new ArrayList<Tank>();
	
	public String hostIp;

	private static GameState instance = null;

	public static GameState getInstance() {
		if (instance == null) {
			instance = new GameState();
		}
		return instance;
	}

	private GameState() {
	}

	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused =paused;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
}
