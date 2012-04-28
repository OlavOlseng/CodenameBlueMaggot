package gfx;

import blueMaggot.Game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Panel;
import javax.swing.JLayeredPane;

public abstract class Menu extends Panel {

	protected Panel buttonPanel;
	protected JLayeredPane layeredPane;

	protected Color menuBg;

	private int width = 500;
	private int height = 314;
	private int border = 5;

	public static Color green = new Color(137, 255, 172);
	public static Color blue = new Color(153, 210, 228);
	public static Color pink = new Color(255, 212, 218);

	public Menu() {
		putCenter(this, width, height);
		setLayout(new GridBagLayout());
		repaint();
		setBackground(Menu.blue);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Menu.blue);
		g.fillRect(border, border, getWidth() - border * 2, getHeight() - border * 2);
		super.paint(g);
	}

	public void putCenter(Component c, int width, int height) {
		c.setBounds(Game.WIDTH / 2 - width / 2, Game.HEIGHT / 2 - height / 2, width, height);
	}
}
