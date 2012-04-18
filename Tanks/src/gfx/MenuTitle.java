package gfx;

import java.awt.Color;
import java.awt.Dimension;

import input.InputHandler;

public class MenuTitle extends Menu {

	private int width = 500;
	private int height = 300;
	private Dimension size = new Dimension(width, height);
	public MenuButton btnExit;

	public MenuTitle(InputHandler input) {
		super(input);

		btnExit = new MenuButton("Exit!", "exit");
		
		super.transparentBgColor = new Color(255, 10, 250, 70);
		// super.buttonPanel.setPreferredSize(size);
		super.setBounds((rootFrameWidth / 2) - (width / 2), (rootFrameHeight / 2) - (height / 2), width, height);
		super.buttonPanel.setBounds((rootFrameWidth / 2) - (width / 2), (rootFrameHeight / 2) - (height / 2), width, height);
		super.ButtonArr.add(new MenuButton("NOO!", ""));
		super.ButtonArr.add(new MenuButton("Okey!", ""));
		super.ButtonArr.add(btnExit);

	}
}
