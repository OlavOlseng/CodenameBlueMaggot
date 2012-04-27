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

	protected GridBagConstraints c = new GridBagConstraints();
	protected Panel buttonPanel;
	protected JLayeredPane layeredPane;

	protected ArrayList<MenuButton> ButtonArr = new ArrayList<MenuButton>();
	protected Dimension menuSize;
	
	protected int border;
	protected Color menuBg;

	public Menu() {

		setLayout(new GridBagLayout());
	}

	public void setUpLayout() {

		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 5;
		c.ipadx = 200;
		c.insets = new Insets(10, 0, 10, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy++;

		for (MenuButton button : ButtonArr) {
			add(button, c);
			c.gridy++;
			System.out.println("adding button");
		}
		validate();
		repaint();
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
