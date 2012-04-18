package gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameDebug extends JPanel implements Runnable {

	BufferedImage backgroundImage;
	int width;
	int height;

	public GameDebug(int width, int height) {
		run();
		this.width = width;
		this.height = height;
		setBounds(0, 0, width, height);
	}

	@Override
	public void run() {

		try {
			backgroundImage = ImageIO.read(new File("./res/temp_background.png"));
			System.out.println("img added");
		} catch (IOException e) {
			System.out.println("File not found.");
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, width, height, null);
	}
}
