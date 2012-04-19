package gfx;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import baseGame.BaseGame;
import baseGame.testGame;

public class MenuPanel extends JFrame {
	
	public MenuPanel(){
		setVisible(true);
		BaseGame t = new testGame();
		t.setVisible(true);
		add(t);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		validate();
		t.init(60);
	}
	public static void main(String [] args){
		
		new MenuPanel();
		
	}
	
}
