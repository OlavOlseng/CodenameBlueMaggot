package gfx;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MenuTitle extends Menu {

	private int width = 500;
	private int height = 300;
	public MenuButton btnExit;
	private Dimension size = new Dimension(width, height);

	public MenuTitle() {
		super();

		buttonPanel.setSize(size);
		backgroundPanel.setSize(size);
		layeredPane.setPreferredSize(size);

//		super.putCenter(this, width, height);

		btnExit = new MenuButton("Exit!", "exit");

		super.transparentBgColor = new Color(255, 10, 250, 70);

		super.ButtonArr.add(new MenuButton("NOO!", ""));
		super.ButtonArr.add(new MenuButton("Okey!", ""));
		super.ButtonArr.add(btnExit);

		super.setUpLayout();

	}

}
