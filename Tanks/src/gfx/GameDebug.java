package gfx;

import input.InputHandler;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GameDebug extends JFrame implements Runnable {

	BufferedImage backgroundImage;
	public int width = 600;
	public int height = 800;
	InputHandler input = new InputHandler();
	private JLayeredPane layeredPane;
	public  MenuTitle menu = new MenuTitle();

	public GameDebug() {
		menu = new MenuTitle();
		menu.setOpaque(false);
//		menu.setVisible(false);

		layeredPane = this.getLayeredPane();
		layeredPane.add(menu, JLayeredPane.POPUP_LAYER);
		layeredPane.setOpaque(false);
		layeredPane.setLayout(new GridBagLayout());

		InputHandler input = new InputHandler(this);
		
		setFocusable(true);
		addKeyListener(input);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackgroundImage();
		setPreferredSize(new Dimension(height, width));

		pack();
		setLocationRelativeTo(null);

		setVisible(true);
	}

	@Override
	public void run() {
		// while(true)
		 repaint();
	}

	public void setBackgroundImage() {

		try {
			backgroundImage = ImageIO.read(new File("./res/temp_background.png"));
		} catch (IOException e) {
			System.out.println("File not found.");
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(backgroundImage, 0, 0, height, width, null);
	}

	public static void main(String[] args) {
		Thread t = new Thread(new GameDebug());
		t.start();
	}

	public  void toggleMenu() {
		if (menu.isVisible())
			menu.setVisible(false);
		else
			menu.setVisible(true);
		repaint();
	}
}