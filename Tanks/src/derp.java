import javax.swing.JFrame;

import baseGame.testGame;


public class derp extends JFrame {
	testGame game;
	public derp(){
		setVisible(true);
		game = new testGame();
		add(game);
	}
	public void  init(){
		game.init();
	}
	public static void main(String[] args) {
		new derp().init();
	}
}
