package baseGame;


import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.KeyStroke;

import blueMaggot.Game;

public class derp extends JFrame {
	Game game;
	public static int playerNumber;

	public derp() {
		setVisible(true);
		game = new Game();
		add(game);
	}

	public void init()  {

	//game.initConnection(true,"");GameState.getInstance().setPlayerNumber(2);
//	game.initConnection(false, "127.0.0.1");GameState.getInstance().setPlayerNumber(1);
//	game.initConnection(false,"169.254.202.27");playerNumber = 1;	
//	game.initConnection(false, "78.91.9.98");
		
	game.startReuglarGame();
	
//		


	}

	public static void main(String[] args) {
		new derp().init();
	}
}
