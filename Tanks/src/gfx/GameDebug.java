package gfx;

import input.InputHandler;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameDebug extends JFrame implements Runnable {

	BufferedImage backgroundImage;
	public static int width = 600;
	public static int height = 800;
	InputHandler input = new InputHandler();

	public GameDebug() {
		addKeyListener(new InputHandler());
		setBackgroundImage();
		setPreferredSize(new Dimension(height, width));

		setVisible(true);

	}

	@Override
	public void run() {
		add(new MenuTitle());
		pack();
		setLocationRelativeTo(null);
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
}
