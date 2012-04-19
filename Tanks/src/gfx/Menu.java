package gfx;

import input.InputHandler;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BoxLayout;
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
//			setPreferredSize(new Dimension(100,300));
		}
	}

	protected GridBagConstraints c = new GridBagConstraints();
	protected JPanel buttonPanel;
	protected MenuBackgroundPanel backgroundPanel;
	protected JLayeredPane layeredPane;

	protected ArrayList<MenuButton> ButtonArr = new ArrayList<MenuButton>();
	protected Dimension menuSize;
	protected Color transparentBgColor;

	protected final int rootFrameHeight = GameDebug.height;
	protected final int rootFrameWidth = GameDebug.width;

	public Menu() {

		setBounds(0, 0, rootFrameWidth, rootFrameHeight);
		setOpaque(false);

		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(rootFrameHeight,rootFrameWidth));

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());

		backgroundPanel = new MenuBackgroundPanel();

		layeredPane.add(backgroundPanel, new Integer(2));
		layeredPane.add(buttonPanel, new Integer(2));

//		add(layeredPane);
		setUp();
		add(buttonPanel);
	}

	public void putCenter(JPanel panel, int width, int height) {
		setBounds((rootFrameWidth / 2) - (width / 2), (rootFrameHeight / 2) - (height / 2), width, height);
	}

	public void setUp() {

		c.gridheight = 1;
		c.gridwidth = 1;

		// setOpaque(false);

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
		}

	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.add(new MenuTitle());
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(new InputHandler());
		frame.setLocationRelativeTo(null);
		
		frame.revalidate();
		frame.repaint();
	}
}
