package baseGame;

import gfx.ResourceManager;

import java.io.File;

import javax.swing.JFrame;

import networking.NetworkObjectType;

import blueMaggot.Game;
import blueMaggot.GameState;

public class derp extends JFrame {
	Game game;
	public static int playerNumber;

	public derp() {
		setVisible(true);
		game = new Game();
		add(game);
	}

	public void init() {

//	game.initConnection(true,"");playerNumber =2;
//	game.initConnection(false,"169.254.202.27");GameState.getInstance().setPlayerNumber(1);	
//	game.initConnection(true,"");playerNumber =2;
//	game.initConnection(false, "127.0.0.1");
//	game.initConnection(false,"169.254.202.27");playerNumber = 1;	
//	game.initConnection(false, "78.91.9.98");
		
	game.startReuglarGame();
	
//		


	}

	public static void main(String[] args) {
		ResourceManager.getInstance().initResources();
		new derp().init();
	

	}
}
