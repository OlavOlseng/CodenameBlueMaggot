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

	class MenuBackgroundPanel extends Panel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(transparentBgColor);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}

	protected GridBagConstraints c = new GridBagConstraints();
	protected JPanel buttonPanel;
	protected MenuBackgroundPanel backgroundPanel;
	protected JLayeredPane layeredPane;

	protected ArrayList<MenuButton> ButtonArr = new ArrayList<MenuButton>();
	protected Dimension menuSize;
	protected Color transparentBgColor;

	public Menu() {

		setLayout(new GridBagLayout());

		layeredPane = new JLayeredPane();
		layeredPane.setOpaque(false);

		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new GridBagLayout());

		backgroundPanel = new MenuBackgroundPanel();

		layeredPane.add(buttonPanel, new Integer(1));
		layeredPane.add(backgroundPanel, new Integer(0));

		add(layeredPane);
	}

	public void setUpLayout() {

		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 5;
		c.ipadx = 100;
		c.insets = new Insets(10, 0, 10, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy++;

		for (MenuButton button : ButtonArr) {
			buttonPanel.add(button, c);
			c.gridy++;
			System.out.println("adding button");
		}
	}

	public void putCenter(Component c, int width, int height) {
		c.setBounds(Game.WIDTH / 2 - width / 2, Game.HEIGHT / 2 - height / 2, width, height);
	}
}
