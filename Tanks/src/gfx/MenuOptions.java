package gfx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import blueMaggot.Game;

public class MenuOptions extends Menu {
	private ArrayList<MenuButton> ButtonArr = new ArrayList<MenuButton>();
	private GridBagConstraints c = new GridBagConstraints();
	private Dimension btnSize = new Dimension(70, 20);

	public MenuOptions(Game game) {
		super();

		// setVisible(false);
		super.border = 5;
		int borderSides = 60;
		super.menuBg = new Color(153, 210, 228);

		TextField fieldIp = new TextField("", 20);
		MenuButton btnApply = new MenuButton("apply", this, game, btnSize);
		MenuButton btnReturn = new MenuButton("return", this, game, btnSize);

		MenuLabel nickPlayerOne = new MenuLabel("playerOne");
		MenuLabel nickPlayerTwo = new MenuLabel("playerTwo");
		MenuLabel connectIp = new MenuLabel("connectIp");
		MenuLabel isHost = new MenuLabel("isHost");
		MenuLabel yes = new MenuLabel("yes", new Dimension(30, 10));
		MenuLabel no = new MenuLabel("no", new Dimension(30, 10));

//		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(10, borderSides, 10, 5);
		c.gridx = 0;
		c.gridy = 0;

		int top = c.gridy;
		int left = c.gridx;

		// left column
		c.weightx = 0.5;
		add(nickPlayerOne, c); // 0,0

		c.gridy++;
		System.out.println(c.weightx);
		add(nickPlayerTwo, c); // 0,1

		c.gridy++;
		add(connectIp, c); // 0,2

		c.gridy++;
		add(isHost, c); // 0,3

		c.weightx = 0.1;
		c.gridx++;
		c.insets = new Insets(10, 5, 10, borderSides);
		add(yes, c); // 5,3

		c.gridx++;
		add(no, c); // 6,3

		// right column
		int bottom = c.gridy;

//		c.weightx = 0.4;
		c.gridwidth = 5;
		c.gridy = top;
		c.gridx = 1;
		add(new TextField("", 20), c); // 5,0
		c.gridy++;
		add(new TextField("", 20), c); // 5,1
		c.gridy++;
		add(new TextField("", 20), c); // 5,2

		c.gridx = 0;
		c.gridy = ++bottom;
		c.ipady = 5;
		c.gridwidth = 1;
//		c.weightx = 1;
		c.insets = new Insets(10, borderSides, 10, 5);
		add(btnApply, c); // 0,6
		c.gridx++;
		c.gridwidth = 5;
		c.insets = new Insets(10, 5, 10, borderSides);
		add(btnReturn, c); // 5,6
	}
}
