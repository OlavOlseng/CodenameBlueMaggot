package blueMaggot;

import java.awt.Dimension;
import java.io.File;

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
	public int playerOneScore = 0;
	public int playerTwoScore = 0;
	public int playerTreeScore = 0;
	public int playerFourScore = 0;
	
	public int playerOneLife = 5;
	public int playerTwoLife = 5;
	public int playerTreeLife = 5;
	public int playerFourLife = 5;
	
	public int playerOneSelectedWeapon;
	public int playerTwoSelectedWeapon;
	public int playerTreeSelectedWeapon;
	public int playerFourSelectedWeapon;
	
	public String nickPlayerOne = "carl";
	public String nickPlayerTwo = "biker bob";
	public String nickPlayerTree = "jesus per";
	public String nickPlayerFoud = "fyrste tom";
	
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
