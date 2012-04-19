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

		btnExit = new MenuButton("Exit!", "exit");

		super.setPreferredSize(size);
		super.layeredPane.setPreferredSize(size);
		super.buttonPanel.setPreferredSize(size);
		super.backgroundPanel.setPreferredSize(size);
		
		super.buttonPanel.setBounds(0, 0, width, height);
		super.backgroundPanel.setBounds(0, 0, width, height);

		super.transparentBgColor = new Color(255, 10, 250, 70);
		
		putCenter(this, width, height);

		super.ButtonArr.add(new MenuButton("NOO!", ""));
		super.ButtonArr.add(new MenuButton("Okey!", ""));
		super.ButtonArr.add(btnExit);

	}
}
