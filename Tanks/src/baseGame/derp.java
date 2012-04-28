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
<<<<<<< HEAD
	
		//olav:"78.91.9.98")
//	game.initConnection(true,"");playerNumber =1;
	game.initConnection(false,"127.0.0.1");playerNumber = 2;	

	
		

	

=======
		double x = 4023;
		
	game.initConnection(true, "");playerNumber =1;
//	game.initConnection(false,"127.0.0.1");playerNumber = 2;	
>>>>>>> 2d8d4b8a97e84b26b7c2e8e7b79fe440318914f0

	}

	public static void main(String[] args) {
		new derp().init();
	}
}
