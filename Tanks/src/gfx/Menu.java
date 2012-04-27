package gfx;

import blueMaggot.Game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public abstract class Menu extends Panel {

	protected Panel buttonPanel;
	protected JLayeredPane layeredPane;

	protected int border;
	protected Color menuBg;
	
	private int width = 500;
	private int height = 300;

	public Menu() {
		putCenter(this, width, height);
		setLayout(new GridBagLayout());
	}


	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(menuBg);
		g.fillRect(border, border, getWidth()-border*2, getHeight()-border*2);
		super.paint(g);
	}

	public void putCenter(Component c, int width, int height) {
		c.setBounds(Game.WIDTH / 2 - width / 2, Game.HEIGHT / 2 - height / 2, width, height);
	}
}
