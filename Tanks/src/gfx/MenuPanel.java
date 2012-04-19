package gfx;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;

import org.omg.PortableInterceptor.DISCARDING;

import baseGame.BaseGame;
import baseGame.testGame;

public class MenuPanel extends JFrame {
	
	public MenuPanel(){
		
		BaseGame t = new testGame();
		t.setVisible(true);
		add(t);
		
		
		t.setPreferredSize(new Dimension(800,600));
		pack();
		setVisible(true);
		t.init(100);
	}
	public static void main(String [] args){
		
		new MenuPanel();
		
	}
	
}
