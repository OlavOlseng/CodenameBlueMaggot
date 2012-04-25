package gfx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import baseGame.testGame;

public class MenuTitle extends Menu {

	private int width = 500;
	private int height = 300;
	public MenuButton btnExit;
	private Dimension size = new Dimension(width, height);

	public MenuTitle() {
		super();

		// u need this
		super.layeredPane.setPreferredSize(size);
		
		buttonPanel.setBounds(0, 0, width, height);
		backgroundPanel.setBounds(0, 0, width, height);

		super.putCenter(this, width, height);

		btnExit = new MenuButton("Exit!", "exit");

		super.transparentBgColor = new Color(255, 10, 250, 70);

		// add buttons for title menu here
		super.ButtonArr.add(new MenuButton("NOO!", ""));
		super.ButtonArr.add(new MenuButton("Okey!", ""));
		super.ButtonArr.add(btnExit);
		
		setVisible(false);
		setOpaque(false);

		super.setUpLayout();
	}
}
