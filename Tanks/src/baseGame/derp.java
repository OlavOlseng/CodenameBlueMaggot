package baseGame;

import javax.swing.JFrame;

import blueMaggot.Game;

public class derp extends JFrame {
	Game game;
	public static int playerNumber;

	public derp() {
		setVisible(true);
		game = new Game();
		add(game);
	}

	public void init() {

//	game.initConnection(true);playerNumber =1;
	game.initConnection(false);playerNumber = 2;
	double x = -2;
	

	
	
	


	}

	public static void main(String[] args) {
		new derp().init();
	}
}
