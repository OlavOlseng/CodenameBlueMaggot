package blueMaggot;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import entity.Tank;

public class GameState {

	public boolean PAUSED = false;

	public boolean isHost = false;
	public boolean running = false;
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
}
