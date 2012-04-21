package gfx;

import input.InputHandler;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public abstract class Menu extends JPanel {

	class MenuBackgroundPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
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
		setOpaque(false);

		layeredPane = new JLayeredPane();
		layeredPane.setOpaque(false);

		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new GridBagLayout());

		backgroundPanel = new MenuBackgroundPanel();
		backgroundPanel.setOpaque(false);

		layeredPane.add(buttonPanel, new Integer(1));
		layeredPane.add(backgroundPanel, new Integer(0));

		add(layeredPane);
	}


	public void setUpLayout() {

		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 20;
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
}
