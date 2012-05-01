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

	private boolean isHost = false;
	private File selectedLevelTerrain = new File("./lvl/Cityscape_terrain.png");
	private File selectedLevelBackground = new File("./lvl/Cityscape_background.png");

	private int width = 1280;
	private int height = 720;
	public Dimension dimension = new Dimension(width, height);

	// customizable player variables!
	ArrayList<Tank> players;

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
	
	public void init(){
		players = new ArrayList<Tank>();
	}
	
	public boolean isHost() {
		return isHost;
	}

	public void setHost(boolean isHost) {
		this.isHost = isHost;
	}

	public File getSelectedLevelTerrain() {
		return selectedLevelTerrain;
	}

	public void setSelectedLevelTerrain(File selectedLevelTerrain) {
		this.selectedLevelTerrain = selectedLevelTerrain;
	}

	public File getSelectedLevelBackground() {
		return selectedLevelBackground;
	}

	public void setSelectedLevelBackground(File selectedLevelBackground) {
		this.selectedLevelBackground = selectedLevelBackground;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Dimension getDimension() {
		return dimension;
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
		this.paused = paused;
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

	public ArrayList<Tank> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Tank> players) {
		this.players = players;
	}
}
