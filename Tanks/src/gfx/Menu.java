package gfx;

import input.InputHandler;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public abstract class Menu extends JPanel implements Runnable {

	protected JLayeredPane layeredPane;
	protected Container content;
	protected JFrame rootFrame;
	protected GridBagConstraints c = new GridBagConstraints();
	protected JPanel buttonPanel;

	protected ArrayList<MenuButton> ButtonArr = new ArrayList<MenuButton>();
	protected Dimension menuSize;
	protected Color transparentBgColor;

	protected final int rootFrameHeight = 600;
	protected final int rootFrameWidth = 800;

	private BufferedImage backgroundImage;

	public Menu(InputHandler input) {
		rootFrame = new JFrame();
		rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rootFrame.setPreferredSize(new Dimension(rootFrameWidth, rootFrameHeight));
		rootFrame.setFocusable(true);
		rootFrame.addKeyListener(input);
		
		setOpaque(false);

		layeredPane = rootFrame.getLayeredPane();
		layeredPane.setBounds(0, 0, rootFrameWidth, rootFrameHeight);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setOpaque(false);

		layeredPane.add(this, new Integer(1));
		layeredPane.add(buttonPanel, new Integer(2));

		// add temp game w/background etc and possibly animation
		layeredPane.add(new GameDebug(rootFrameWidth, rootFrameHeight), new Integer(0));
	}

	public void putCenter(int width, int height) {
		setBounds((rootFrameWidth / 2) - (width / 2), (rootFrameHeight / 2) - (height / 2), width, height);
	}

	@Override
	public void run() {

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

		rootFrame.pack();
		rootFrame.setVisible(true);
		rootFrame.setLocationRelativeTo(null);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, rootFrameWidth, rootFrameHeight, null);
		g.setColor(transparentBgColor);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
